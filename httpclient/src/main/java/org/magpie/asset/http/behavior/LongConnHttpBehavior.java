package org.magpie.asset.http.behavior;

import java.util.ResourceBundle;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 启用http请求长链接支持
 * <p>
 * 用户通过提供"httpclient.properties"文件配制长链接相关参数,例如:
 * <p>
 * <code>
 * maxConns=10
 * <code>
 * 
 * @author chenheng
 *
 */
public class LongConnHttpBehavior extends AbstractHttpBehavior {
	private static Logger logger = LoggerFactory.getLogger(LongConnHttpBehavior.class);
	private static PoolingHttpClientConnectionManager poolingmgr;

	static {
		poolingmgr = new PoolingHttpClientConnectionManager();
		try {
			ResourceBundle resource = ResourceBundle.getBundle("httpclient");
			int maxConns = Integer.parseInt(resource.getString("maxConns"));
			poolingmgr.setDefaultMaxPerRoute(maxConns);
			poolingmgr.setMaxTotal(2 * maxConns);
		} catch (Exception e) {
			logger.warn("!!!! httpclient config file not exist or key(s) illegal: {}", e);
			poolingmgr.setDefaultMaxPerRoute(10);
			poolingmgr.setMaxTotal(20);
		}
	};

	@Override
	public CloseableHttpClient build() {
		return HttpClients.custom().setConnectionManager(poolingmgr).build();
	}

}
