package com.gpengtao.java;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by pengtao.geng on 2017/10/24.
 */
public class Other {

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10000; i++) {
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet("http://h5.itcast.cn/pp/zhufuAjax");

            CloseableHttpResponse response = client.execute(httpGet);

            System.out.println(response.getStatusLine());

        }


    }
}
