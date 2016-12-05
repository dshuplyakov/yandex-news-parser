package ru.dlamanche.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dlamanche.entity.NewsDto;
import ru.dlamanche.provider.StaticContext;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Date: 03.12.2016
 * Time: 22:16
 *
 * @author Dmitry Shuplyakov
 */
public class ParseNews {

    private static final Logger log = LoggerFactory.getLogger(JobParseNews.class);

    public static void run(String url) {
        log.info("Start parsing " + url);
        try {
            InputStream stream = StaticContext.httpClient.sendGet(url);
            List<NewsDto> newsDtos = StaticContext.staxParser.parseNews(new BufferedInputStream(stream));
            StaticContext.storage.addToMap(newsDtos, url);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
