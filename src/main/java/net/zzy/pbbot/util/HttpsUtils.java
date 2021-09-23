package net.zzy.pbbot.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author jxygzzy
 */
public class HttpsUtils {

    public static String post(String url, String paramJson) throws Exception {
        String res;
        CloseableHttpClient httpClient = null;
        httpClient = HttpClientFactory.buildSSLCloseableHttpClient();
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Content-Type", "application/json;charset=UTF-8");
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();
        httppost.setConfig(requestConfig);
        StringEntity data = new StringEntity(paramJson, StandardCharsets.UTF_8);
        httppost.setEntity(data);
        CloseableHttpResponse response = httpClient.execute(httppost);
        HttpEntity entity = response.getEntity();
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            res = EntityUtils.toString(entity, "UTF-8");
            response.close();
        } else {
            res = EntityUtils.toString(entity, "UTF-8");
            response.close();
            throw new IllegalArgumentException(res);
        }
        HttpClientFactory.destroyHttpClient();
        return res;
    }

    public static String get(String url, Map<String, String> params) throws Exception {
        String res;
        CloseableHttpClient httpClient = null;
        httpClient = HttpClientFactory.buildSSLCloseableHttpClient();
        // 定义请求的参数
        URIBuilder uriBuilder = new URIBuilder(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue());
        }

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //response 对象
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            res = EntityUtils.toString(entity, "UTF-8");
            response.close();
        } else {
            res = EntityUtils.toString(entity, "UTF-8");
            response.close();
            throw new IllegalArgumentException(res);
        }
        HttpClientFactory.destroyHttpClient();
        return res;
    }

}
