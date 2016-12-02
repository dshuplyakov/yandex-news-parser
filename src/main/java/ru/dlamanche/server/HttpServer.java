package ru.dlamanche.server;

import com.google.inject.Inject;
import org.eclipse.jetty.server.*;
import ru.dlamanche.DataCollector;


/**
 * Date: 02.12.2016
 * Time: 17:04
 *
 * @author Dmitry Shuplyakov
 */
public class HttpServer {

    @Inject
    DataCollector dataCollector;

    public void run() {
        Server server = new Server(8080);
        server.setHandler(new NewsHandler(dataCollector));
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
