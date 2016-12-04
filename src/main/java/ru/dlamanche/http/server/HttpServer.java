package ru.dlamanche.http.server;

import com.google.inject.Inject;
import org.eclipse.jetty.server.Server;

import java.net.BindException;


/**
 * Date: 02.12.2016
 * Time: 17:04
 *
 * @author Dmitry Shuplyakov
 */
public class HttpServer {

    @Inject
    PageHandler pageHandler;

    private int port = 8080;

    public void run() {
        Server server = new Server(port);
        server.setHandler(pageHandler);
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
