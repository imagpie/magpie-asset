package org.magpie.asset.http.behavior;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 默认的http请求服务
 * 
 * @author chenheng
 *
 */
public class DefaultHttpBehavior extends AbstractHttpBehavior {

	@Override
	public CloseableHttpClient build() {
		return HttpClients.createDefault();
	}

}
