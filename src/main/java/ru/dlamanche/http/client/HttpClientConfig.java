package ru.dlamanche.http.client;

import com.google.common.base.MoreObjects;

/**
 * Date: 02.12.2016
 * Time: 12:46
 *
 * @author Dmitry Shuplyakov
 */
public class HttpClientConfig {

    public final int maxConnections;
    public final int connectionTimeout;
    public final int requestTimeout;
    public final int retryCount;
    public final boolean requestSentRetryEnabled;

    public HttpClientConfig(int maxConnections, int connectionTimeout, int requestTimeout, int retryCount,
                            boolean requestSentRetryEnabled) {
        this.maxConnections = maxConnections;
        this.connectionTimeout = connectionTimeout;
        this.requestTimeout = requestTimeout;
        this.retryCount = retryCount;
        this.requestSentRetryEnabled = requestSentRetryEnabled;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("maxConnections", maxConnections)
                .add("connectionTimeout", connectionTimeout)
                .add("requestTimeout", requestTimeout)
                .add("retryCount", retryCount)
                .add("requestSentRetryEnabled", requestSentRetryEnabled)
                .toString();
    }
}
