package com.tingyun.auto.framework.browser;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.thoughtworks.selenium.Wait;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.SystemClock;
import com.tingyun.auto.framework.driver.WebdriverFactory;
/**
* @author :chenjingli 
* @version ：2015-5-11 下午4:13:44 
* @decription: 选择浏览器启动入口
 */
public class DriverBrowser{
	
	private RemoteWebDriver remoteWebDriver;
	
	private WebDriver webDriver;
	
	private boolean isRemote = SeleniumSettings.IS_REMOTE;
	
	private static Logger logger = LoggerFactory.getLogger(DriverBrowser.class);
	
	private static int stepInterval = SeleniumSettings.STEP_INTERVAL;
	
	private static int timeout = SeleniumSettings.TIMEOUT;
	
	private JavascriptExecutor javaScriptExecutor;
	/**
	 * 添加选择本地还是远程启动的构造方法
	 */
	public DriverBrowser(BrowserType type) {
		try{
			logger.info("通过传入参数选择对应的浏览器：{}-----是否选择远程调用：{}",type.toString(),isRemote);
			if(isRemote){
				webDriver = WebdriverFactory.createWebDriver(type).getwebDriver();
				if(webDriver==null){
					logger.error("反向代理生成的webdriver为null-------{}",webDriver);
				}
				webDriver.manage().window().maximize();
			}else{
				remoteWebDriver = WebdriverFactory.createWebDriver(type).getRemWebDriver();
				if(remoteWebDriver == null){
					logger.error("反向代理生成的remoteWebDriver为null-------{}",remoteWebDriver);
				}
			}
		}catch(Exception e){
			logger.error("选择浏览器类型，启动仿真浏览器异常---------：{}-堆栈信息-{}",type,e);
		}
	}
	
	public RemoteWebDriver getRemoteWebDriver() {
		return remoteWebDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	/**
     * 
     * @Description 打开一个网页
     * @param url
     *            网页URL
     */
    public void open(String url) {
        pause(stepInterval);
        try {
            webDriver.get(url);
            logger.info("Opened url " + url);
        } catch (AssertionError e) {
        	fail("Failed to open url " + url);
            handleFailure("Failed to open url " + url);
        }
    }

    /**
     * Pause
     * 
     * @param time
     *            in millisecond
     */
    public void pause(int time) {
        if (time <= 0) {
            return;
        }
        try {
            Thread.sleep(time);
          //  logger.info("Pause " + time + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * sendKeys to textbox
     * 
     * @param webElement
     * @param text
     */
    public void sendKeys(WebElement webElement, String text) {
        if (webElement == null) {
        	logger.error("Not found Element , SendKeys not issued!");
            handleFailure("Not found Element , SendKeys not issued!");
        }
        pause(stepInterval);
        try {
            webElement.sendKeys(text);
            logger.info("SendKeys : " + text);
        } catch (AssertionError e) {
        	fail("Failed to sendKeys , SendKeys not issued!"+text);
            handleFailure("Failed to sendKeys , SendKeys not issued!" + text);
        }
    }

    /**
     * 
     * @Description Select an option by visible text from &lt;select&gt; web
     *              element.
     * @param selector
     *            下拉框（只支持select,其他自定义的下拉框全都不支持)
     * @param option
     *            被选项文本
     */
    public void select(WebElement selector, String option,String value) {
    	pause(stepInterval);
        Select select = null;
        try {
        if(selector.isDisplayed()){
        	select = new Select(selector);
        	if("value".equals(value)){
        		select.selectByValue(option);
        	}else if("text".equals(value)){
        		select.selectByVisibleText(option);
        	}else if("index".equals(value)){
        		select.selectByIndex(Integer.parseInt(option));
        	}
        }else{
        	logger.error("select 元素未找到：elemetn location is:{}",getWebElementContent(selector));
        	fail(selector+"元素未找到！");
        }
        } catch (AssertionError e) {
        	fail("Failed to select! element is====:"+selector+"选择的值是===："+option+"通过"+value+"方式来选择");
            handleFailure("Failed to select! element is====:"+selector+"选择的值是===："+option+"通过"+value+"方式来选择");
        }
    }

    /**
     * 清空文本
     * 
     * @date : May 7, 2014
     * @ClassName : clear
     */
    public void clear(WebElement webElement) {
        if (webElement == null) {
            handleFailure("Not found Element , clear not issued!");
        }
        pause(stepInterval);
        try {
            webElement.clear();
            logger.info("clear ok!");
        } catch (Exception e) {
            handleFailure("Failed clear , clear not issued!");
        }
    }

    /**
     * Click the page element
     * 
     * @ClassName : click
     * @param webElement
     */
    public void click(WebElement webElement) {
        if (webElement == null) {
            handleFailure("Not found Element ,Failed click!");
        }
        if (webElement.isDisplayed()) {
            SystemClock clock = new SystemClock();
            long endTime = clock.laterBy(timeout);
            isClickAble(webElement, clock, endTime);
        } else {
        	logger.error("click 元素未找到：elemetn location is:{}",getWebElementContent(webElement));
            handleFailure("Element display is none!");
        }
    }
    
    public void timeOUt(final WebElement webElement){
    	WebDriverWait wait = new WebDriverWait(webDriver,timeout);
		
		wait.until(new ExpectedCondition<WebElement>(){  
			@Override  
			public WebElement apply(WebDriver d) {  
				return webElement;  
			}}).click();  
    }
    
    private void isClickAble(final WebElement webElement, SystemClock clock, long endTime) {
        try {
        	timeOUt(webElement);
            //webElement.click();
            logger.info("Clicked successed! " + getWebElementContent(webElement));
        } catch (AssertionError e) {
            if (clock.isNowBefore(endTime)) {
                logger.info("try to click ");
                isClickAble(webElement, clock, endTime);
            } else {
            	fail("Failed to click " + getWebElementContent(webElement));
                handleFailure("Failed to click " + getWebElementContent(webElement));
            }
        }
    }

    /**
     * 模拟回车操作
     * 
     * @date Dec 12, 2013
     */
//    public void submit() {
//        new Actions(webDriver).sendKeys(Keys.ENTER).perform();
//        refresh();
//    }

    /**
     * Hover on the page element
     * 
     * @ClassName : mouseOver
     * @param webElement
     *            void
     */
//    public void mouseOver(WebElement webElement) {
//        pause(stepInterval);
//        Actions builder = new Actions(webDriver);
//        builder.moveToElement(webElement).perform();
//        builder.release();
//    }

    /**
     * Switch window/tab
     * 
     * @param windowTitle
     *            the window/tab's title
     */
    public void selectWindow(String windowTitle) {
        pause(stepInterval);
        String parentWindow = webDriver.getWindowHandle();
        for (String window : webDriver.getWindowHandles()) {
            if (window != parentWindow) {
                webDriver.switchTo().window(window);
                logger.info(webDriver.getTitle());
                if (webDriver.getTitle() == windowTitle) {
                    logger.info("Switched to window " + windowTitle);
                    break;
                }
            }
        }
    }

    /**
     * 
     * @Description 进入iFrame
     * @param frame
     */
    public void enterFrame(WebElement frame) {
        pause(stepInterval);
        webDriver.switchTo().frame(frame);
        logger.info("Entered iframe " + getWebElementContent(frame));
    }

    /**
     * Leave the iframe
     */
    public void leaveFrame() {
        pause(stepInterval);
        webDriver.switchTo().defaultContent();
        logger.info("Left the iframe");
    }

    /**
     * Refresh the browser
     */
    public void refresh() {
        pause(stepInterval);
        webDriver.navigate().refresh();
        logger.info("Refreshed");
    }

//    private void expectTextNotExist(final String text, int timeout) {
//        if (isTextPresent(text))
//            handleFailure("Found undesired text " + text);
//        if (timeout <= 0) {
//            logger.info("Not found undesired text " + text);
//        } else {
//            expectTextNotExist(text, timeout - stepInterval);
//        }
//    }

    /**
     * Expect an text not exist on the page<br>
     * Expect text not exist, but found after timeout => Assert fail<br>
     * Here <b>exist</b> means <b>visible</b>
     * 
     */
//    public void expectTextNotExist(final String text) {
//        expectTextNotExist(text, SeleniumSettings.TIMEOUT);
//    }

    /**
     * Expect an text exist on the page<br>
     * Expect text exist, but not found after timeout => Assert fail<br>
     * Here <b>exist</b> means <b>visible</b>
     * 
     */
//    public void expectTextExist(final String text) {
//        expectTextExist(text, SeleniumSettings.TIMEOUT);
//    }

//    private void expectTextExist(final String text, int timeout) {
//        try {
//            new Wait() {
//
//                @Override
//                public boolean until() {
//                    return isTextPresent(text);
//                }
//            }.wait("Failed to find text " + text, timeout);
//        } catch (Exception e) {
//            e.printStackTrace();
//            handleFailure("Failed to find text " + text);
//        }
//        logger.info("Found desired text " + text);
//    }

    /**
     * 
     * @Description 判断文本是否存在
     * @param text
     *            待检测的文本
     * @return true 存在，false 不存在
     */
//    public boolean isTextPresent(String text) {
//        pause(stepInterval);
//        WebDriverBackedSelenium selenium = new WebDriverBackedSelenium(webDriver, GlobalSettings.LIEPIN_URL);
//        logger.info("looking for " + text);
//        return selenium.isTextPresent(text);
//    }

    /**
     * Expect an element exist on the page<br>
     * 判断元素存在，返回true Expect element exist, but not found after timeout => Assert
     * fail<br>
     * Here <b>exist</b> means <b>visible</b>
     * 
     * @param webElement
     *            the expected element's xpath
     */
    public void expectElementExist(final WebElement webElement,String...strings) {
        if (webElement == null) {
            handleFailure("Not found Element ,Failed click!");
        }
        expectElementExist(webElement, timeout,strings);
    }

    private void expectElementExist(final WebElement webElement, int timeout , String...strings) {
    	try{
    		
    		WebDriverWait wait = new WebDriverWait(webDriver,timeout);
    		
    		wait.until(new ExpectedCondition<WebElement>(){  
    			@Override  
    			public WebElement apply(WebDriver d) {  
    				return webElement;  
    			}}).isDisplayed();  
    		
    		logger.info("Found desired element " + getWebElementContent(webElement));
    	}catch(Exception e){
    		if(timeout>0){
    			for (String string : strings) {
    				handleFailure("期望"+string+"元素未展现，超时时间"+timeout);
				}
    		}else{
    			logger.error("时间设置小于0");
    		}
    	}
    }
    
    
    /**
     * Expect an element not exist on the page<br>
     * 判断元素不存在，返回true Expect element not exist, but found after timeout =>
     * Assert fail<br>
     * Here <b>exist</b> means <b>visible</b>
     * 
     * @param timeout
     *            timeout in millisecond
     */
    public void expectElementNotExist(final WebElement webElement) {
        expectElementNotExist(webElement, timeout);
    }

    private void expectElementNotExist(final WebElement webElement, int timeout) {
        if (isElementPresent(webElement))
            handleFailure("Found undesired element " + getWebElementContent(webElement));
        if (timeout <= 0) {
            logger.info("Not found undesired element " + getWebElementContent(webElement));
        } else {
            expectElementNotExist(webElement, timeout - stepInterval);
        }
    }

    /**
     * @Description 判断元素是否存在
     * @param webElement
     * @return true 存在，false 不存在
     */
    public boolean isElementPresent(WebElement webElement) {
        pause(stepInterval);
        logger.info("looking for " + getWebElementContent(webElement));
        return webElement.isDisplayed();
    }

    /**
     * 执行javascript命令
     * 
     * @ClassName : executeScript
     * @param script
     *            javascript命令
     * @return 返回结果</br>没有返回值时null Object
     */
    public Object executeScript(String script) {
        this.javaScriptExecutor = (JavascriptExecutor) webDriver;
        if (javaScriptExecutor == null) {
            handleFailure("executeScript is null");
            return null;
        }
        return javaScriptExecutor.executeScript(script);
    }

    private void handleFailure(String notice) {
        String log = "Error info >>" + notice + " >> ";
        logger.error(log);
        Reporter.log(log );
        Assert.fail(log);
    }

    /**
     * 
    * @Description 保存截图到自定义路径
    *  @param path 自定义路径
    * @author chenDoInG
    * @return String 自定义路径
    /**
	 * 用例执行成功/失败时 进行截图
	 * 
	 * @param desc
	 *            截图名称描述
	 */
    public void failScreenShot(String desc) {
		File scrFile = ((TakesScreenshot) webDriver)
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

    /**
     * 获取webElement内容
     * 
     * @author : chenDoInG
     * @date : 2013-11-14
     * @ClassName : getWebElementContent
     * @param webElement
     * @return 返回webElement内容 String 如linktext = “内容”
     */
    private String getWebElementContent(WebElement webElement) {
        if (webElement == null)
            return null;
        try {

            String[] splits = webElement.toString().split("->");
            String result = splits[1].replaceFirst("]", "");
            return "Element location is -->"+result;
        } catch (Exception e) {
            return "can't catch name";
        }
    }


    /**
     * @Description  
     * 			获取当前的url地址
     * @throws
     */
    public String getCurUrl() {
        return webDriver.getCurrentUrl();
    }

    /**
     * @Description  
     * 			选择匹配的titile页面
     * @param windowTitle
     * @throws
     */
    public void selectWindowTitle(String windowTitle) {
        try {
            for (String winHandle : webDriver.getWindowHandles()) {
                if (webDriver.switchTo().window(winHandle).getTitle().equals(windowTitle)) {
                    break;
                }
            }
            logger.info("Switch to Windows Handle : " + windowTitle);
        } catch (Exception e) {
            handleFailure("Faild to switchTo Windows Handle!\r\n" + e.toString());
        }
    }

    /**
     * @Description  
     * 			可以选择新打开的窗口，即LastWindow
     * @throws
     */
    public void selectLastWindow() {
        try {
            int num = 0;
            for (String winHandle : webDriver.getWindowHandles()) {
                if (webDriver.getWindowHandles().size() - 1 == num) {
                    webDriver.switchTo().window(winHandle);
                }
                num++;
            }
            logger.info("Switch to Windows Handle : ");
        } catch (Exception e) {
            handleFailure("Faild to switchTo Windows Handle!\r\n" + e.toString());
        }
    }


    /**
     * @Description  
     * 			切换到给定的windowHandle，然后关闭窗口
     * @param closeWinHandle
     * 			will be close windows handle
     * @throws
     */
    public void closeWindow(String closeWinHandle) {
        selectWindowTitle(closeWinHandle);
        try {
            pause(stepInterval);
            webDriver.close();
            logger.info("Close Windows Handle : " + closeWinHandle);
        } catch (Exception e) {
            handleFailure("Failed to Close Windows Handle!\r\n" + e.toString());
        }
    }

    
    /**
     * 
    * @Description 获取cookie中的用户id
    * @author chenDoInG
    * @return String 用户id
     */
    public String getUserIDByCookie() {
    	 return getInfoFromCookie("user_id");
    }

    public String getUserLoginByCookie(){
    	return getInfoFromCookie("user_login");
    }
    /**
    * @Description 获取cookie中的用户名
    *  @return    
    * @author chenDoInG
    * @return String 
    * @throws
    */
    public String getInfoFromCookie(String cookieName) {
        try {
        	String cookieStr = URLDecoder.decode(getCookie(cookieName), "utf-8");
        	return cookieStr;
        } catch (Exception e) {
        	logger.warn("获取cookie异常！");
            return "";
        }
    }

    /**
     * @Description  添加一个自定义的cookie，一般用来控制弹层
     * @param cookieName
     * 				cookie的key
     * @param cookieValue
     * 				cookie的value
     * @return
     * @throws
     */
    public boolean setCookie(String cookieName,String cookieValue)
    {
    	try {
    		Cookie cookie = new Cookie(cookieName, cookieValue);
        	webDriver.manage().addCookie(cookie);
		} catch (Exception e) {
			return false;
		}
    	return true;
    }
    
    /**
     * @Description  
     * 		删除一个已存在的cookie
     * @param cookieName
     * 		cookie的key
     * @return
     * @throws
     */
    public boolean delCookie(String cookieName)
    {
    	try {
    		Cookie cookie = new Cookie(cookieName,"");
        	webDriver.manage().deleteCookie(cookie);
		} catch (Exception e) {
			return false;
		}
    	return true;
    }
    
    /**
     * @Description  
     * 		获取一个已存在的cookie的value值
     * @param cookieName
     * @return
     * @author hanzhen 2015年3月29日下午1:55:51
     * @throws
     */
    public String getCookie(String cookieName)
    {
    	try {
            Cookie cookie = webDriver.manage().getCookieNamed(cookieName);
            String cookieValue = cookie.getValue().toString();
            if( cookieValue== null || cookieValue == "")
         	{
         		logger.warn("警告：获取"+cookieName+"为空！");
         	}
            return cookieValue;
		} catch (Exception e) {
			return "";
		}
    }
    
    
    
    /**
     * 此方法用于返回页面文本信息
     * 
     * @param webElement
     * @return 页面文本
     */
    public String getPageText(WebElement webElement) {
    	pause(stepInterval);
    	if(webElement.isDisplayed()){
    		String pageText = webElement.getText();
    		return pageText;
    	}else{
    		logger.error("element is none location is:{}",webElement);
    		return "";
    	}
    }
    
    public int getElementNums(List<WebElement> elements){
    	int	number = elements.size();
		return number;
    }
    
    /**
     * @Description 关闭已经打开的浏览器模拟器
     */
    public void quit() {
        if (webDriver != null)
        	webDriver.quit();
    }
    
    public void close(){
    	if (webDriver != null)
        	webDriver.close();
    }
    
    /**
	 * 控制Alert的确认与取消
	 * 
	 * @param flag
	 *            根据传递的参数accept、dismiss，进行相应的操作
	 */
	public void confirmAlert(boolean flag) {
		try {
			Alert alert = webDriver.switchTo().alert();
			if (alert == null) {
				return;
			}
			if (flag) {
				alert.accept();
			} else {
				alert.dismiss();
			}
		} catch (NoAlertPresentException e) {
			return;
		}
	}
	
	
}
