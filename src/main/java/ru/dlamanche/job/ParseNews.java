package ru.dlamanche.job;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dlamanche.http.client.HttpClient;
import ru.dlamanche.storage.HazelcastProvider;
import ru.dlamanche.xml.StaxParser;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Date: 03.12.2016
 * Time: 22:16
 *
 * @author Dmitry Shuplyakov
 */
public class ParseNews {

    @Inject
    static HttpClient httpClient;

    @Inject
    static StaxParser staxParser;

    @Inject
    static HazelcastProvider storage;

    private static final Logger log = LoggerFactory.getLogger(JobParseNews.class);

    public static void run(String url) {
        log.info("Start parsing " + url);
        try {
            InputStream stream = httpClient.sendGet(url);
            storage.addToMap(staxParser.parseNews(new BufferedInputStream(stream)), url);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
