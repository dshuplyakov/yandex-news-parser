package ru.dlamanche.entity;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Date: 02.12.2016
 * Time: 13:28
 *
 * @author Dmitry Shuplyakov
 */
public class NewsDto implements Serializable {

    private String title;
    private String descr;
    private String link;
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("title", title)
                .add("descr", descr)
                .add("link", link)
                .add("pubDate", pubDate)
                .toString();
    }
}
