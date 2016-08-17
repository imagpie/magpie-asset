package org.magpie.asset.http.behavior;

import java.io.IOException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的http请求服务
 * 
 * @author chenheng
 *
 */
public class DefaultHttpBehavior extends AbstractHttpBehavior {
	private final Logger logger = LoggerFactory.getLogger(DefaultHttpBehavior.class);
	@Override
	public CloseableHttpClient buildClient() {
		return HttpClients.createDefault();
	}

	@Override
	public void releaseClient(CloseableHttpClient client) {
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				logger.warn("!!!! release client occured io exception: {}", e);
			}
		}
	}

}
