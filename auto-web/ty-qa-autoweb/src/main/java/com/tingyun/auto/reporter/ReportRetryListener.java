package com.tingyun.auto.reporter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
/**
* @author :chenjingli 
* @version ：2015-5-7 下午5:13:16 
* @decription:
 */

public class ReportRetryListener implements IAnnotationTransformer  {
	@SuppressWarnings("rawtypes")
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {

        IRetryAnalyzer retry = annotation.getRetryAnalyzer();
        if (retry == null) {
            //annotation.setRetryAnalyzer(RetryAnalyzer.class);
            annotation.setRetryAnalyzer(ReportRetryFail.class);
        }
    }
}	
