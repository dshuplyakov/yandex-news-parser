package ru.dlamanche;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dlamanche.config.MainConfig;
import ru.dlamanche.http.HttpClient;
import ru.dlamanche.parser.StaxParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 02.12.2016
 * Time: 12:47
 *
 * @author Dmitry Shuplyakov
 */
public class NewsImporter {

    private static final Logger log = LoggerFactory.getLogger(NewsImporter.class);

    @Inject
    HttpClient httpClient;

    @Inject
    MainConfig mainConfig;

    @Inject
    StaxParser staxParser;

    public void start() {

        try {
            InputStream stream = httpClient.sendGet(mainConfig.yandexUrl);
            staxParser.parseNews(new BufferedInputStream(stream));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
