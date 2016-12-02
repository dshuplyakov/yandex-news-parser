package ru.dlamanche;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dlamanche.config.MainConfig;
import ru.dlamanche.entity.NewsDto;
import ru.dlamanche.http.HttpClient;
import ru.dlamanche.xml.StaxParser;
import ru.dlamanche.server.HttpServer;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;

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

    @Inject
    DataCollector dataCollector;

    @Inject
    HttpServer httpServer;

    public void start() {
        loadNews();
        httpServer.run();
    }

    private void loadNews() {
        try {
            InputStream stream = httpClient.sendGet(mainConfig.yandexUrl);
            dataCollector.setNews(staxParser.parseNews(new BufferedInputStream(stream)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
