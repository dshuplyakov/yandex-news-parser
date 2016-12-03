package ru.dlamanche;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dlamanche.config.MainConfig;
import ru.dlamanche.http.HttpClient;
import ru.dlamanche.server.HttpServer;
import ru.dlamanche.storage.StorageProvider;
import ru.dlamanche.xml.StaxParser;

import java.io.BufferedInputStream;
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

    @Inject
    StorageProvider storage;

    @Inject
    HttpServer httpServer;

    @Inject
    Scheduler scheduler;

    public void start() {
        scheduler.start();

        if (storage.getList("news").isEmpty()) {
            loadNews();
        } else {
            log.info("Already load, skipped");
        }
        httpServer.run();
    }

    private void loadNews() {
        try {
            InputStream stream = httpClient.sendGet(mainConfig.yandexUrl);
            storage.setList(staxParser.parseNews(new BufferedInputStream(stream)), "news");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
