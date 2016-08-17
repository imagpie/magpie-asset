package org.magpie.asset.http.client;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LongConnHttpClientTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGet() {
		String rlt = LongConnHttpClient.get("http://www.baidu.com");
		Assert.assertNotNull(rlt);
	}
	
	@Test
	public void testGet2() {
		String rlt = LongConnHttpClient.get("http://www.baidu.com");
		Assert.assertNotNull(rlt);
	}
}
