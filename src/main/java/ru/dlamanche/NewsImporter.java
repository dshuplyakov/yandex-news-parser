package ru.dlamanche;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dlamanche.http.server.HttpServer;
import ru.dlamanche.provider.StaticContext;
import ru.dlamanche.statistic.Scheduler;

/**
 * Date: 02.12.2016
 * Time: 12:47
 *
 * @author Dmitry Shuplyakov
 */
public class NewsImporter {

    private static final Logger log = LoggerFactory.getLogger(NewsImporter.class);

    @Inject
    HttpServer httpServer;

    @Inject
    Scheduler scheduler;

    @Inject
    StaticContext staticContext;

    public void start() {
        staticContext.configure();
        scheduler.showStats();
        httpServer.run();
    }


}
