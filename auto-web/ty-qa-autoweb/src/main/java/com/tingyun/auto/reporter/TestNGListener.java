/**
 * 
 */
package com.tingyun.auto.reporter;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

import com.tingyun.auto.common.Constant;


/**
 * @author chenjingli
 *
 */
public class TestNGListener extends TestListenerAdapter {


	@Override
	public void onStart(ITestContext testContext) {
		Constant.suites.add(testContext.getSuite().getName());
		 System.out.println(testContext.getName() + "\t"
		 + testContext.getStartDate());
	}
}
