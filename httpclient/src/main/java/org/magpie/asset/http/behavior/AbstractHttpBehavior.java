package org.magpie.asset.http.behavior;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHttpBehavior implements HttpBehavior {
	private final Logger logger = LoggerFactory.getLogger(AbstractHttpBehavior.class);

	public abstract CloseableHttpClient buildClient();
	public abstract void releaseClient(CloseableHttpClient client);

	public String get(String url) {
		return get(url, null);
	}

	public String get(String url, String params) {
		logger.info(">>>> http get request url={}, params={}", url, params);
		StringBuilder sb = new StringBuilder(url);
		if (StringUtils.isNotBlank(params)) {
			sb.append("?").append(params);
		}
		CloseableHttpClient client = buildClient();
		HttpGet get = new HttpGet(sb.toString());
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			int code = response.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String rlt = EntityUtils.toString(entity);
					logger.debug(">>>> content: {}", rlt);
					return rlt;
				}
			} else {
				logger.warn("!!!! response code: {}", code);
			}
		} catch (IOException e) {
			logger.warn("!!!! IO Exception: {}", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.warn("!!!! IO Exception: {}", e);
				}
			}
			releaseClient(client);
		}
		return null;
	}

	public String post(String url) {
		return post(url, null);
	}

	public String post(String url, String params) {
		return post(url, params, ContentType.APPLICATION_JSON);
	}

	public String post(String url, String params, ContentType contentType) {
		logger.debug(">>>> http post request: url={}, params={}, contentType={}", url, params, contentType);
		CloseableHttpClient client = buildClient();
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse response = null;
		if (StringUtils.isNotBlank(params)) {
			StringEntity entity = new StringEntity(params, contentType);
			entity.setChunked(true);
			post.setEntity(entity);
		}
		try {
			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				HttpEntity hentity = response.getEntity();
				if (hentity != null) {
					String rlt = EntityUtils.toString(hentity);
					logger.debug(">>>> content: {}", rlt);
					return rlt;
				}
			} else {
				logger.warn("!!!! response code: {}", code);
			}
		} catch (IOException e) {
			logger.warn("!!!! IO Exception: {}", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.warn("!!!! IO Exception: {}", e);
				}
			}
			releaseClient(client);
		}
		return null;
	}

	public String put(String url) {
		return put(url, null);
	}

	public String put(String url, String params) {
		return put(url, params, ContentType.APPLICATION_JSON);
	}

	public String put(String url, String params, ContentType contentType) {
		logger.debug(">>>> http put request: url={}, params={}, contentType={}", url, params, contentType);
		CloseableHttpClient client = buildClient();
		HttpPut put = new HttpPut(url);
		CloseableHttpResponse response = null;
		if (StringUtils.isNotBlank(params)) {
			StringEntity entity = new StringEntity(params, contentType);
			entity.setChunked(true);
			put.setEntity(entity);
		}
		try {
			response = client.execute(put);
			int code = response.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				HttpEntity hentity = response.getEntity();
				if (hentity != null) {
					String rlt = EntityUtils.toString(hentity);
					logger.debug(">>>> content: {}", rlt);
					return rlt;
				}
			} else {
				logger.warn("!!!! response code: {}", code);
			}
		} catch (IOException e) {
			logger.warn("!!!! IO Exception: {}", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.warn("!!!! IO Exception: {}", e);
				}
			}
			releaseClient(client);
		}
		return null;
	}

	public String delete(String url) {
		return delete(url, null);
	}

	public String delete(String url, String params) {
		logger.info(">>>> http delete request url={}, params={}", url, params);
		StringBuilder sb = new StringBuilder(url);
		if (StringUtils.isNotBlank(params)) {
			sb.append("?").append(params);
		}
		CloseableHttpClient client = buildClient();
		HttpDelete del = new HttpDelete(sb.toString());
		CloseableHttpResponse response = null;
		try {
			response = client.execute(del);
			int code = response.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String rlt = EntityUtils.toString(entity);
					logger.debug(">>>> content: {}", rlt);
					return rlt;
				}
			} else {
				logger.warn("!!!! response code: {}", code);
			}
		} catch (IOException e) {
			logger.warn("!!!! IO Exception: {}", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.warn("!!!! IO Exception: {}", e);
				}
			}
			releaseClient(client);
		}
		return null;
	}
}
