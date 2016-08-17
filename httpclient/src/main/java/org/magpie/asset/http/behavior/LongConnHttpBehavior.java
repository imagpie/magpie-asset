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
 * <code> maxConns=10 <code>
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
			int maxPerRoute = Integer.parseInt(resource.getString("maxConnsPerRoute"));
			int maxTotal = Integer.parseInt(resource.getString("maxConnsTotal"));
			poolingmgr.setDefaultMaxPerRoute(maxPerRoute);
			poolingmgr.setMaxTotal(maxTotal);
		} catch (Exception e) {
			logger.warn("!!!! please refer the example httpclient.properties in jar and provide a right "
					+ "config file to overwrite the default value!", e);
			poolingmgr.setDefaultMaxPerRoute(10);
			poolingmgr.setMaxTotal(20);
		}
	};

	@Override
	public CloseableHttpClient buildClient() {
		return HttpClients.custom().setConnectionManager(poolingmgr).build();
	}

	@Override
	public void releaseClient(CloseableHttpClient client) {
		logger.debug(">>>> do nothing for long connection http behavior!!");
	}

}
