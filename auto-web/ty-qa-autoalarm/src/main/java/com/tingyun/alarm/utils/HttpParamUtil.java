package com.tingyun.alarm.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tingyun.alarm.entity.ParamsBean;


public class HttpParamUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<String, String> paramValues = new HashMap<String, String>();
		paramValues.put("b", "2");
		paramValues.put("a", "1");
		paramValues.put("c", "3");
		paramValues.put("d", "4");
		paramValues.put("e", "5");
		System.out.println("post params：：：：" + getParams("post", paramValues));
		System.out.println("get params：：：：" + getParams("get", paramValues));
		System.out.println("post params：：：：" + getParamsOrderByKey("post", paramValues));
		System.out.println("get params：：：：" + getParamsOrderByKey("get", paramValues));

	}

	/**
	 * 得到参数列表字符串
	 * 
	 * @param method
	 *            请求类型 get or post
	 * @param paramValues
	 *            参数map对象
	 * @return 参数列表字符串
	 */
	public static String getParams(String method, Map<String, String> paramValues) {
		String params = "";
		Set<String> key = paramValues.keySet();
		String beginLetter = "";
		if (method.equalsIgnoreCase("get")) {
			beginLetter = "?";
		}
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			if (params.equals("")) {
				params += beginLetter + s + "=" + paramValues.get(s);
			} else {
				params += "&" + s + "=" + paramValues.get(s);
			}
		}
		return params;
	}
	public static String getParams(String method, List<ParamsBean> list) {
		String params = "";
		String beginLetter = "";
		if (method.equalsIgnoreCase("get")) {
			beginLetter = "?";
		}
		for (ParamsBean paramsBean : list) {
			if (params.equals("")) {
				params += beginLetter + paramsBean.getKey() + "=" + paramsBean.getValue();
			} else {
				params += "&" + paramsBean.getKey() + "=" + paramsBean.getValue();
			}
		}
		return params;
	}
	/**
	 * 按照key排序得到参数列表字符串
	 * 
	 * @param method
	 *            请求类型 get or post
	 * @param paramValues
	 *            参数map对象
	 * @return 参数列表字符串
	 */
	public static String getParamsOrderByKey(String method, Map<String, String> paramValues) {
		String params = "";
		Set<String> key = paramValues.keySet();
		String beginLetter = "";
		if (method.equalsIgnoreCase("get")) {
			beginLetter = "?";
		}
		List<String> paramNames = new ArrayList<String>(paramValues.size());
		paramNames.addAll(paramValues.keySet());
		Collections.sort(paramNames);
		for (String paramName : paramNames) {

			if (params.equals("")) {
				params += beginLetter + paramName + "=" + paramValues.get(paramName);
			} else {
				params += "&" + paramName + "=" + paramValues.get(paramName);
			}
		}

		return params;
	}

}
