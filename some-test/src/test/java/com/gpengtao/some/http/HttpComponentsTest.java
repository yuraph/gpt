/*
 * Copyright (c) 2018 Wormpex.com. All Rights Reserved.
 */
package com.gpengtao.some.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author pengtao.geng on 2018/6/21 上午11:49.
 */
public class HttpComponentsTest {

	@Test
	public void test() throws IOException {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000)
				.setSocketTimeout(5000)
				.build();

		HttpPost request = new HttpPost("http://www.baidu.com");
		request.setConfig(requestConfig);

		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = httpClient.execute(request);
		System.out.println(EntityUtils.toString(response.getEntity()).length());

		response.close();

		request.releaseConnection();

		CloseableHttpResponse response2 = httpClient.execute(new HttpPost("http://www.baidu.com"));
		System.out.println(EntityUtils.toString(response2.getEntity()).length());
		response2.close();

		httpClient.close();
	}

	@Test
	public void test_with_timeout() throws IOException {
		Content content = Request.Get("https://github.com/")
				.connectTimeout(1000)
				.socketTimeout(10)
				.execute()
				.returnContent();

		System.out.println(content.asString());

	}

	@Test
	public void test_no_timeout() throws IOException {
		// 必须设置connectTimeout、socketTimeout，否则会一致等待server返回
		Content content = Request.Get("http://localhost:8080/admin/backdoor/test/v2").execute().returnContent();

		System.out.println(content.asString());
	}

	@Test
	public void test_fluent_hc() throws IOException {
		Content content = Executor.newInstance()
				.use(new BasicCookieStore())    // cookie
				.execute(Request.Get("http://localhost:8080/admin/backdoor/test/v2"))
				.returnContent();

		System.out.println(content);
	}
}
