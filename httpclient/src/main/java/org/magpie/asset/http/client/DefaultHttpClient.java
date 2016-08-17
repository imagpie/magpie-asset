package org.magpie.asset.http.client;

import org.apache.http.entity.ContentType;
import org.magpie.asset.http.behavior.DefaultHttpBehavior;
import org.magpie.asset.http.behavior.HttpBehavior;

/**
 * 默认的http请求服务(未使用长链接, 每个请求都重新发起新的连接!)
 * 
 * @author chenheng
 */
public class DefaultHttpClient {
	static HttpBehavior behavior = new DefaultHttpBehavior();

	public static String get(String url) {
		return behavior.get(url);
	}

	public static String get(String url, String params) {
		return behavior.get(url, params);
	}

	public static String post(String url) {
		return behavior.post(url);
	}

	public static String post(String url, String params) {
		return behavior.post(url, params);
	}

	public static String post(String url, String params, ContentType contentType) {
		return behavior.post(url, params, contentType);
	}

	public static String put(String url) {
		return behavior.put(url);
	}

	public static String put(String url, String params) {
		return behavior.put(url, params);
	}

	public static String put(String url, String params, ContentType contentType) {
		return behavior.put(url, params, contentType);
	}

	public static String delete(String url) {
		return behavior.delete(url);
	}

	public static String delete(String url, String params) {
		return behavior.delete(url, params);
	}
}
