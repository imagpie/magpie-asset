package org.magpie.asset.http.behavior;

import org.apache.http.entity.ContentType;

public interface HttpBehavior {
	public String get(String url);

	public String get(String url, String params);

	public String post(String url);

	public String post(String url, String params);

	public String post(String url, String params, ContentType contentType);

	public String put(String url);

	public String put(String url, String params);

	public String put(String url, String params, ContentType contentType);

	public String delete(String url);

	public String delete(String url, String params);
}
