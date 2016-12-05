package ru.dlamanche.provider;

import com.google.inject.Inject;
import ru.dlamanche.http.client.HttpClient;
import ru.dlamanche.storage.HazelcastProvider;
import ru.dlamanche.xml.StaxParser;

/**
 * Date: 05.12.2016
 * Time: 13:30
 * <p>
 * Inject beans in static context
 *
 * @author Dmitry Shuplyakov
 */
public class StaticContext {

    @Inject
    public HttpClient httpClientInjected;
    public static HttpClient httpClient;

    @Inject
    public StaxParser staxParserInjected;
    public static StaxParser staxParser;

    @Inject
    public HazelcastProvider storageInjected;
    public static HazelcastProvider storage;

    public void configure() {
        StaticContext.httpClient = httpClientInjected;
        StaticContext.staxParser = staxParserInjected;
        StaticContext.storage = storageInjected;
    }
}
