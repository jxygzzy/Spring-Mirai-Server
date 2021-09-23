package net.zzy.pbbot.util;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author jxygzzy
 */
public class HttpClientFactory {
    public static CloseableHttpClient client;

    public static CloseableHttpClient buildSSLCloseableHttpClient()
            throws Exception {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
                new TrustStrategy() {
                    // 信任所有
                    @Override
                    public boolean isTrusted(X509Certificate[] chain,
                                             String authType) throws CertificateException {
                        return true;
                    }
                }).build();
        if (client == null) {
            // ALLOW_ALL_HOSTNAME_VERIFIER:这个主机名验证器基本上是关闭主机名验证的,实现的是一个空操作，并且不会抛出javax.net.ssl.SSLException异常。
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultCookieStore(new BasicCookieStore()).build();
        }
        return client;
    }

    public static void destroyHttpClient() {
        client = null;
    }
}
