package ru.dlamanche.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import ru.dlamanche.DataCollector;

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
public class NewsHandler extends AbstractHandler {

    public static final String APPLICATION_JSON_CONTENT_TYPE = "application/json; charset=utf-8";
    DataCollector dataCollector;
    ObjectMapper mapper;

    public NewsHandler(DataCollector dataCollector) {
        this.dataCollector = dataCollector;
        mapper = new ObjectMapper();
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException,
            ServletException {
        response.setContentType(APPLICATION_JSON_CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(mapper.writeValueAsString(dataCollector.getNews()));
        baseRequest.setHandled(true);
    }

}