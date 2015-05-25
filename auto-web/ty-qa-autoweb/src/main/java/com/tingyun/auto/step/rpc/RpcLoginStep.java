package com.tingyun.auto.step.rpc;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.Test;

import com.tingyun.auto.page.rpc.RpcLoginPage;
import com.tingyun.auto.page.rpc.report.ReportPage;
import com.tingyun.auto.step.GlobalStep;
import com.tingyun.auto.step.LoginStep;

/**
* @author :chenjingli 
* @version ：2015-5-18 下午5:29:41 
* @decription: rpc系统登录用例
 */
public class RpcLoginStep extends GlobalStep{
	public static final String rpcDesLogin = "rpc系统登录测试用例";
	@Test(description=rpcDesLogin)
	public void testRpcLogin(){
		pinfo(LoginStep.class,rpcDesLogin+caseStart);
		try {
			RpcLoginPage reportPage = new RpcLoginPage(driverBrowser);
			reportPage.login();
		}catch(Error e){
			driverBrowser.failScreenShot("testRpcLogin");
			fail(rpcDesLogin+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testRpcLogin");
			throw new TestNGException(rpcDesLogin+FAIL + e.getMessage(), e);
		} 
	}
}
