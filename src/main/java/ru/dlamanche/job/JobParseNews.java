package ru.dlamanche.job;

import java.io.Serializable;

/**
 * Date: 03.12.2016
 * Time: 20:14
 *
 * @author Dmitry Shuplyakov
 */
public class JobParseNews implements Runnable, Serializable {

    String url;

    public JobParseNews(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        ParseNews.run(url);
    }
}