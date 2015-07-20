package com.tingyun.auto.reporter;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;



public class TestResultListener extends TestListenerAdapter {
	


	Logger logger = LoggerFactory.getLogger(TestResultListener.class);
	@Override
	public void onTestFailure(ITestResult tr) {
		String methodName = tr.getMethod().getMethodName();
		this.failScreenShot(methodName);
		logger.info(methodName+"was running failed!");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		// TODO Auto-generated method stub
		String methodName = tr.getMethod().getMethodName();
		logger.info(methodName+"was running skipped!");
	}
	
	/**
	* @author : chenjingli
	* @decription 截图
	* @return
	 */
	 public void failScreenShot(String desc) {
			File scrFile = ((TakesScreenshot) DriverBrowser.getWebDriver())
					.getScreenshotAs(OutputType.FILE);
			logger.error("开始截图，名称{}", desc + ".jpg");
			File screenshot = new File("test-output/html" + File.separator
					+ "failScreenShot" + File.separator + desc + ".jpg");
			try {
				FileUtils.copyFile(scrFile, screenshot);
			} catch (IOException e) {
				logger.error("截图操作失败" + e.getMessage());
			}

		}

}
