package com.dandine.benjamin.rssreader.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by benjamindandine on 07/02/2017.
 */
@Root(strict = false)
public class Enclosure implements Serializable {

    public static final String SHARED_PREFERENCE_ITEM_ENCLOSURE_URL = "shared_preference_item_enclosure_url";

    @Attribute(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
