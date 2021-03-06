//=============================================================================
// Copyright 2006-2013 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================

package com.tingyun.auto.reporter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IClass;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.tingyun.auto.common.Constant;
import com.tingyun.auto.utils.HtmlMail;
import com.tingyun.auto.utils.ShareSmbConfig;
import com.tingyun.auto.utils.Smb;


/**
 * Enhanced HTML reporter for TestNG that uses Velocity templates to generate
 * its output.
 * 
 * @author Daniel Dyer
 * @modify by  chenjingli
 */
public class HTMLReporter extends AbstractReporter{

	/**
	 * slf4j logback
	 */
	private final static Logger logger = LoggerFactory
			.getLogger(HTMLReporter.class);

	private static final String FRAMES_PROPERTY = "com.tingyun.reportng.frames";
	private static final String ONLY_FAILURES_PROPERTY = "com.tingyun.reportng.failures-only";

	private static final String TEMPLATES_PATH = "com/tingyun/reportng/templates/html/";
	private static final String INDEX_FILE = "index.html";
	private static final String SUITES_FILE = "suites.html";
	private static final String OVERVIEW_FILE = "overview.html";
	private static final String GROUPS_FILE = "groups.html";
	private static final String RESULTS_FILE = "results.html";
	private static final String OUTPUT_FILE = "output.html";
	private static final String CUSTOM_STYLE_FILE = "custom.css";

	private static final String SUITE_KEY = "suite";
	private static final String SUITES_KEY = "suites";
	private static final String GROUPS_KEY = "groups";
	private static final String RESULT_KEY = "result";
	private static final String FAILED_CONFIG_KEY = "failedConfigurations";
	private static final String SKIPPED_CONFIG_KEY = "skippedConfigurations";
	private static final String FAILED_TESTS_KEY = "failedTests";
	private static final String SKIPPED_TESTS_KEY = "skippedTests";
	private static final String PASSED_TESTS_KEY = "passedTests";
	private static final String ONLY_FAILURES_KEY = "onlyReportFailures";

	private static final String REPORT_DIRECTORY = "html";
	private static final Comparator<ITestNGMethod> METHOD_COMPARATOR = new TestMethodComparator();
	private static final Comparator<ITestResult> RESULT_COMPARATOR = new TestResultComparator();
	private static final Comparator<IClass> CLASS_COMPARATOR = new TestClassComparator();

	public HTMLReporter() {
		super(TEMPLATES_PATH);
	}

	/**
	 * Generates a set of HTML files that contain data about the outcome of the
	 * specified test suites.
	 * 
	 * @param suites
	 *            Data about the test runs.
	 * @param outputDirectoryName
	 *            The directory in which to create the report.
	 */
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectoryName) {
		removeEmptyDirectories(new File(outputDirectoryName));

		boolean useFrames = System.getProperty(FRAMES_PROPERTY, "true").equals(
				"true");
		boolean onlyFailures = System.getProperty(ONLY_FAILURES_PROPERTY,
				"false").equals("true");

		File outputDirectory = new File(outputDirectoryName, REPORT_DIRECTORY);
		outputDirectory.mkdirs();

		try {
			if (useFrames) {
				createFrameset(outputDirectory);
			}
			sortBySuiltStartTime(suites);
			createOverview(suites, outputDirectory, !useFrames, onlyFailures);
			createSuiteList(suites, outputDirectory, onlyFailures);
			createGroups(suites, outputDirectory);
			createResults(suites, outputDirectory, onlyFailures);
			createLog(outputDirectory, onlyFailures);
			copyResources(outputDirectory);
			//Smb.smbPut(System.getProperty("user.dir")+ ShareSmbConfig.getLocalPath());
			//logger.info("上传smb共享服务器完成");
			generateMailHtml(suites, META.getReportCurrent());
			logger.info("测试报告完成");
		} catch (Exception ex) {
			throw new ReportNGException("Failed generating HTML report.", ex);
		}
	}

	/**
	 * Group test methods by class and sort alphabetically.
	 */
	private void sortBySuiltStartTime(List<ISuite> suites) {
		ArrayList<ISuite> newSuites = new ArrayList<ISuite>();
		for (String suiteName : Constant.suites) {
			for (ISuite suite : suites) {
				if (suite.getName().equals(suiteName)) {
					newSuites.add(suite);
				}
			}
		}
		suites = newSuites;
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	private static void generateMailHtml(List<ISuite> suites, Date reportTime) {
		ResourceBundle bundle = ResourceBundle.getBundle("ftp");
		String to = bundle.getString("toMail");
		if (to == null) {
			return;
		}
		String[] toMail = bundle.getString("toMail").split(";");
//		String temp = ResourceBundle.getBundle("selenium").getString("env");
//
//		String env = "";
//		if ("dev".equals(temp)) {
//			env = "开发";
//		} else if ("test".equals(temp)) {
//			env = "测试";
//		} else if ("sit".equals(temp)) {
//			env = "回归";
//		} else if ("pro".equals(temp)) {
//			env = "生产";
//		}

		//String url ="\\\\"+ShareSmbConfig.getSharePath() + INDEX_FILE;
		String url = "http://192.168.2.58:9090/jenkins/workspace/saas-auto/test-output/html/index.html";

		int passed = 0;
		int fail = 0;
		int skip = 0;

		StringBuilder html = new StringBuilder("<html>");
		StringBuilder sb = new StringBuilder("");
		html.append("<h4>详细结果html:  <a href="+url+">"+url+"</a></h4><br>");
	
		for (ISuite suite : suites) {
			int lineCount = 0;
			if (suite.getAllMethods() == null
					|| suite.getAllMethods().size() == 0) {
				continue;
			}
			html.append("<h1 style='text-align:center;color:blue'>测 试 报 告</h1>");
			
			if (lineCount == 0) {
				sb.append("<h2 style='margin-left:40px;'>");
			} else {
				sb.append("<h2>");
			}
			
			Map<String, ISuiteResult> results = suite.getResults();
			Iterator<String> it = suite.getResults().keySet().iterator();
			int pass1=0;
			int fail1=0;
			while (it.hasNext()) {
				/**
				 * 整理出所需要的数据
				 */
				ISuiteResult result = results.get(it.next());
				ITestContext testContext = result.getTestContext();
				ITestNGMethod[] methods = testContext.getAllTestMethods();
				Set<ITestResult> setResults = new HashSet<>();
				Set<ITestResult> setResults1 = new HashSet<>();
				Set<ITestResult> setResults2 = new HashSet<>();
				String description = null;
				//String methodName  = null;
				int status = 0;
				int status1 = 0;
				int status2 = 0;
				/**
				 * 产品线模块名称  test结果统计
				 */
				pass1 = testContext.getPassedTests().size();
				fail1 = testContext.getFailedTests().size()+testContext.getSkippedTests().size();
				passed = passed+pass1;
				fail = fail+fail1;
				int total = pass1+fail1;
						
				sb.append("<h3 style='color:blue;'>"+suite.getName()+"====>"+testContext.getName()+"\t  结果总计：" + total + "\t通过："
						+ pass1 + "\t失败："
						+fail1+ "</h3>");
				
				for (ITestNGMethod iTestNGMethod : methods) {
					description = iTestNGMethod.getDescription();
					//methodName = iTestNGMethod.getMethodName();
					setResults = testContext.getPassedTests().getResults(iTestNGMethod);
					setResults1 = testContext.getFailedTests().getResults(iTestNGMethod);
					setResults2 = testContext.getSkippedTests().getResults(iTestNGMethod);
					if(setResults!=null && setResults.size()!=0){
						for (ITestResult iTestResult : setResults) {
							status = iTestResult.getStatus();
							if(status==1 ){
								sb.append("<h4 style='margin-left:60px;color:green;'>" +
										"<span style='width:450px;float:left'>用例描述："+description+"</span><span>&nbsp;&nbsp;&nbsp;&nbsp;状态：PASS</span></h4>");
							}else if(status ==2 || status==3){
								sb.append("<h4 style='margin-left:60px;color:red;'>" +
										"<span style='width:450px;float:left'>用例描述："+description+"</span><span>&nbsp;&nbsp;&nbsp;&nbsp;状态：FAIL</span></h4>");
							}
						}
						
					}else if(setResults1!=null && setResults1.size()!=0){
						for (ITestResult iTestResult : setResults1) {
							status1 = iTestResult.getStatus();
							if(status1==1 ){
								sb.append("<h4 style='margin-left:60px;color:green;'>" +
										"<span style='width:450px;float:left'>用例描述："+description+"</span><span>&nbsp;&nbsp;&nbsp;&nbsp;状态：PASS</span></h4>");
							}else if(status1 ==2 || status1==3){
								sb.append("<h4 style='margin-left:60px;color:red;'>" +
										"<span style='width:450px;float:left'>用例描述："+description+"</span><span>&nbsp;&nbsp;&nbsp;&nbsp;状态：FAIL</span></h4>");
							}
						}
					}else if(setResults2!=null && setResults2.size()!=0){
						for (ITestResult iTestResult : setResults2) {
							status2 = iTestResult.getStatus();
							if(status2==1 ){
								sb.append("<h4 style='margin-left:60px;color:green;'>" +
										"<span style='width:450px;float:left'>用例描述："+description+"</span><span>&nbsp;&nbsp;&nbsp;&nbsp;状态：PASS</span></h4>");
							}else if(status2 ==2 || status2==3){
								sb.append("<h4 style='margin-left:60px;color:red;'>" +
										"<span style='width:450px;float:left'>用例描述："+description+"</span><span>&nbsp;&nbsp;&nbsp;&nbsp;状态：FAIL</span></h4>");
							}
						}
					}
					
				}
				
				/**
				 *  总的测试结果统计
				 */
			
		
				
			}
			lineCount++;
			
		}
		html.append(
				"<h2 style='text-align:center;color:blue;'>" + "总体结果统计："
						+ (passed + fail) + "\t通过:" + passed
						+ "&nbsp;&nbsp;失败:" + fail
						+ "&nbsp;&nbsp;通过率:"
						+ (passed * 100 / (passed + fail)) + " %</h2>");
		
		
		html.append(sb.toString()).append("</html>");
		
		String subject = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分ss秒")
		.format(reportTime)
		+ "  "
		+ "auto"
		+ "环境      "
		+ bundle.getString("subject");
		HtmlMail.send(toMail, subject, html.toString());

	}

	/**
	 * Create the index file that sets up the frameset.
	 * 
	 * @param outputDirectory
	 *            The target directory for the generated file(s).
	 */
	private void createFrameset(File outputDirectory) throws Exception {
		VelocityContext context = createContext();
		generateFile(new File(outputDirectory, INDEX_FILE), INDEX_FILE
				+ TEMPLATE_EXTENSION, context);
	}

	private void createOverview(List<ISuite> suites, File outputDirectory,
			boolean isIndex, boolean onlyFailures) throws Exception {
		VelocityContext context = createContext();
		context.put(SUITES_KEY, suites);
		context.put(ONLY_FAILURES_KEY, onlyFailures);
		generateFile(new File(outputDirectory, isIndex ? INDEX_FILE
				: OVERVIEW_FILE), OVERVIEW_FILE + TEMPLATE_EXTENSION, context);
	}

	/**
	 * Create the navigation frame.
	 * 
	 * @param outputDirectory
	 *            The target directory for the generated file(s).
	 */
	private void createSuiteList(List<ISuite> suites, File outputDirectory,
			boolean onlyFailures) throws Exception {
		VelocityContext context = createContext();
		context.put(SUITES_KEY, suites);
		context.put(ONLY_FAILURES_KEY, onlyFailures);
		generateFile(new File(outputDirectory, SUITES_FILE), SUITES_FILE
				+ TEMPLATE_EXTENSION, context);
	}

	/**
	 * Generate a results file for each test in each suite.
	 * 
	 * @param outputDirectory
	 *            The target directory for the generated file(s).
	 */
	private void createResults(List<ISuite> suites, File outputDirectory,
			boolean onlyShowFailures) throws Exception {
		int index = 1;
		for (ISuite suite : suites) {
			int index2 = 1;
			for (ISuiteResult result : suite.getResults().values()) {
				boolean failuresExist = result.getTestContext()
						.getFailedTests().size() > 0
						|| result.getTestContext().getFailedConfigurations()
								.size() > 0;
				if (!onlyShowFailures || failuresExist) {
					VelocityContext context = createContext();
					context.put(RESULT_KEY, result);
					context.put(FAILED_CONFIG_KEY, sortByTestClass(result
							.getTestContext().getFailedConfigurations()));
					context.put(SKIPPED_CONFIG_KEY, sortByTestClass(result
							.getTestContext().getSkippedConfigurations()));

					context.put(FAILED_TESTS_KEY, sortByTestClass(result
							.getTestContext().getFailedTests()));

					context.put(SKIPPED_TESTS_KEY, sortByTestClass(result
							.getTestContext().getSkippedTests()));
					context.put(PASSED_TESTS_KEY, sortByTestClass(result
							.getTestContext().getPassedTests()));
					String fileName = String.format("suite%d_test%d_%s", index,
							index2, RESULTS_FILE);
					generateFile(new File(outputDirectory, fileName),
							RESULTS_FILE + TEMPLATE_EXTENSION, context);
				}
				++index2;
			}
			++index;
		}
	}

	/**
	 * Group test methods by class and sort alphabetically.
	 */
	private SortedMap<IClass, List<ITestResult>> sortByTestClass(
			IResultMap results) {
		SortedMap<IClass, List<ITestResult>> sortedResults = new TreeMap<IClass, List<ITestResult>>(
				CLASS_COMPARATOR);
		for (ITestResult result : results.getAllResults()) {
			List<ITestResult> resultsForClass = sortedResults.get(result
					.getTestClass());
			if (resultsForClass == null) {
				resultsForClass = new ArrayList<ITestResult>();
				sortedResults.put(result.getTestClass(), resultsForClass);
			}
			int index = Collections.binarySearch(resultsForClass, result,
					RESULT_COMPARATOR);
			if (index < 0) {
				index = Math.abs(index + 1);
			}
			resultsForClass.add(index, result);
		}
		return sortedResults;
	}

	/**
	 * Generate a groups list for each suite.
	 * 
	 * @param outputDirectory
	 *            The target directory for the generated file(s).
	 */
	private void createGroups(List<ISuite> suites, File outputDirectory)
			throws Exception {
		int index = 1;
		for (ISuite suite : suites) {
			SortedMap<String, SortedSet<ITestNGMethod>> groups = sortGroups(suite
					.getMethodsByGroups());
			if (!groups.isEmpty()) {
				VelocityContext context = createContext();
				context.put(SUITE_KEY, suite);
				context.put(GROUPS_KEY, groups);
				String fileName = String.format("suite%d_%s", index,
						GROUPS_FILE);
				generateFile(new File(outputDirectory, fileName), GROUPS_FILE
						+ TEMPLATE_EXTENSION, context);
			}
			++index;
		}
	}

	/**
	 * Generate a groups list for each suite.
	 * 
	 * @param outputDirectory
	 *            The target directory for the generated file(s).
	 */
	private void createLog(File outputDirectory, boolean onlyFailures)
			throws Exception {
		if (!Reporter.getOutput().isEmpty()) {
			VelocityContext context = createContext();
			context.put(ONLY_FAILURES_KEY, onlyFailures);
			generateFile(new File(outputDirectory, OUTPUT_FILE), OUTPUT_FILE
					+ TEMPLATE_EXTENSION, context);
		}
	}

	/**
	 * Sorts groups alphabetically and also sorts methods within groups
	 * alphabetically (class name first, then method name). Also eliminates
	 * duplicate entries.
	 */
	private SortedMap<String, SortedSet<ITestNGMethod>> sortGroups(
			Map<String, Collection<ITestNGMethod>> groups) {
		SortedMap<String, SortedSet<ITestNGMethod>> sortedGroups = new TreeMap<String, SortedSet<ITestNGMethod>>();
		for (Map.Entry<String, Collection<ITestNGMethod>> entry : groups
				.entrySet()) {
			SortedSet<ITestNGMethod> methods = new TreeSet<ITestNGMethod>(
					METHOD_COMPARATOR);
			methods.addAll(entry.getValue());
			sortedGroups.put(entry.getKey(), methods);
		}
		return sortedGroups;
	}

	/**
	 * Reads the CSS and JavaScript files from the JAR file and writes them to
	 * the output directory.
	 * 
	 * @param outputDirectory
	 *            Where to put the resources.
	 * @throws IOException
	 *             If the resources can't be read or written.
	 */
	private void copyResources(File outputDirectory) throws IOException {
		copyClasspathResource(outputDirectory, "reportng.css", "reportng.css");
		copyClasspathResource(outputDirectory, "reportng.js", "reportng.js");
		// If there is a custom stylesheet, copy that.
		File customStylesheet = META.getStylesheetPath();

		if (customStylesheet != null) {
			if (customStylesheet.exists()) {
				copyFile(outputDirectory, customStylesheet, CUSTOM_STYLE_FILE);
			} else {
				// If not found, try to read the file as a resource on the
				// classpath
				// useful when reportng is called by a jarred up library
				InputStream stream = ClassLoader.getSystemClassLoader()
						.getResourceAsStream(customStylesheet.getPath());
				if (stream != null) {
					copyStream(outputDirectory, stream, CUSTOM_STYLE_FILE);
				}
			}
		}
	}
}
