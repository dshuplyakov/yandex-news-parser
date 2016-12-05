package ru.dlamanche.http.client;

import com.google.inject.Singleton;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 02.12.2016
 * Time: 12:47
 *
 * @author Dmitry Shuplyakov
 */
@Singleton
public class HttpClient {

    private static final Logger log = LoggerFactory.getLogger(HttpClient.class);

    @NotNull
    private CloseableHttpClient httpClient;

    @NotNull
    private String httpClientName;

    public HttpClient(@NotNull String httpClientName, @NotNull HttpClientConfig conf) {

        this.httpClientName = httpClientName;

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(conf.maxConnections);
        cm.setMaxTotal(conf.maxConnections);

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                                .setSocketTimeout(conf.requestTimeout)
                                .setConnectTimeout(conf.connectionTimeout)
                                .build())
                .setRetryHandler(
                        new DefaultHttpRequestRetryHandler(conf.retryCount, conf.requestSentRetryEnabled))
                .build();

        shutdownHook();
    }

    public void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    log.info("Closing httpClient " + httpClientName);
                    httpClient.close();
                } catch (IOException e) {
                    log.error("Can't close httpClient " + httpClientName + " : " + e);
                }
            }
        });
    }

    /**
     * Бросает IOException если не удалось выполнить запрос, либо код ответа < 200 или > 300
     *
     * @param uri URI
     * @return Body
     * @throws IOException
     */
    @NotNull
    public InputStream sendGet(@NotNull String uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        log.info(httpGet.toString());
        HttpEntity httpEntity = null;
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream result = httpResponse.getEntity().getContent();
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() < 200 || statusLine.getStatusCode() > 300) {
                throw new IOException(httpClientName + " request failed. HTTP code: " + statusLine.getStatusCode()
                        + "\n Request URL: " + uri);
            }
            log.info(httpClientName + " return " + statusLine);
            return result;
        } finally {
            EntityUtils.consumeQuietly(httpEntity);
        }
    }
}