package ru.dlamanche.http.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import ru.dlamanche.storage.HazelcastProvider;
import ru.dlamanche.storage.TaskCollector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date: 02.12.2016
 * Time: 17:30
 *
 * @author Dmitry Shuplyakov
 */
public class PageHandler extends AbstractHandler {

    public static final String APPLICATION_JSON_CONTENT_TYPE = "application/json; charset=utf-8";

    @Inject
    HazelcastProvider storageProvider;

    @Inject
    TaskCollector taskCollector;

    ObjectMapper mapper;

    public PageHandler() {
        mapper = new ObjectMapper();
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException,
            ServletException {

        switch (target) {
            case "/addJob":
                addJob(request, response);
                break;
            default:
                hello(request, response);
        }

        response.setContentType(APPLICATION_JSON_CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
    }

    private void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("Hello");
    }

    private void addJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getParameter("url");
        if (url != null) {
            taskCollector.addNewsUrl(url);
            response.getWriter().println("Added to queue: " + url);
        }

    }

}