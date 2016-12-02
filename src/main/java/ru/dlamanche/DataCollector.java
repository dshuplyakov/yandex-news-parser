package ru.dlamanche;

import ru.dlamanche.entity.NewsDto;

import java.util.List;

/**
 * Date: 02.12.2016
 * Time: 17:24
 *
 * @author Dmitry Shuplyakov
 */
public class DataCollector {

    private List<NewsDto> news;

    public List<NewsDto> getNews() {
        return news;
    }

    public void setNews(List<NewsDto> news) {
        this.news = news;
    }
}
