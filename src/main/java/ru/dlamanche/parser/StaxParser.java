package ru.dlamanche.parser;

import com.google.common.base.Strings;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dlamanche.entity.NewsDto;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 02.12.2016
 * Time: 13:25
 *
 * @author Dmitry Shuplyakov
 */
public class StaxParser {


    private static final Logger log = LoggerFactory.getLogger(StaxParser.class);

    @NotNull
    public List<NewsDto> parseNews(BufferedInputStream bufferedInputStream) throws XMLStreamException {


        log.info("Start parsing videos from xml...");

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader;
        try {
            reader = factory.createXMLStreamReader(bufferedInputStream);
        } catch (XMLStreamException e) {
            throw new IllegalStateException("I/O error during integration", e);
        }

        List<NewsDto> result = new ArrayList<>();
        NewsDto newsDto = new NewsDto();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "item":
                            newsDto = new NewsDto();
                            break;

                        case "title":
                            newsDto.setTitle(reader.getElementText().trim());
                            break;

                        case "link":
                            newsDto.setLink(reader.getElementText().trim());
                            break;


                        case "description":
                            newsDto.setDescr(reader.getElementText().trim());
                            break;


                        case "pubDate":
                            newsDto.setPubDate(reader.getElementText().trim());
                            break;

                    }

                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "item":
                            result.add(newsDto);
                            break;
                    }
            }
        }

        log.info("Parsed news: " + result.size());
        log.info(result.toString());
        return result;
    }

}
