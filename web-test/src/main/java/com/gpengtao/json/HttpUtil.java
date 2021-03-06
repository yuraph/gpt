package com.gpengtao.json;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by pengtao.geng on 2015/6/13.
 */
public class HttpUtil {

    public static void test() {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://cn.bing.com/");

        try {
            CloseableHttpResponse response = client.execute(httpGet);
            Header[] allHeaders = response.getAllHeaders();
            StatusLine statusLine = response.getStatusLine();

            ProtocolVersion protocolVersion = response.getProtocolVersion();
            Locale locale = response.getLocale();

            HttpEntity entity = response.getEntity();

            String string = EntityUtils.toString(entity);

        } catch (IOException e) {
        }
    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://h5.itcast.cn/pp/zhufuAjax");

        CloseableHttpResponse response = client.execute(httpGet);

        System.out.println(response.getStatusLine());


    }

}
