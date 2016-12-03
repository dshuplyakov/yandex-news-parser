package ru.dlamanche.server;

import com.google.inject.Inject;
import org.eclipse.jetty.server.*;
import ru.dlamanche.storage.StorageProvider;

import java.net.BindException;
import java.util.Random;


/**
 * Date: 02.12.2016
 * Time: 17:04
 *
 * @author Dmitry Shuplyakov
 */
public class HttpServer {

    @Inject
    StorageProvider storageProvider;

    private int port = 8080;

    public void run() {
        Server server = new Server(port);
        server.setHandler(new NewsHandler(storageProvider));
        try {
            server.start();
            server.join();
        } catch (BindException e) {
            port++;
            run();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
