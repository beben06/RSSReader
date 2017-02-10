package com.dandine.benjamin.rssreader.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by benjamindandine on 07/02/2017.
 */
@Root(strict = false)
public class Item implements Serializable {

    @Element(name = "title")
    public String title;

    @Element(name = "link")
    public String link;

    @Element(name = "description")
    public String description;

    @Element(name = "pubDate")
    public String pubDate;

    @Element(name = "guid")
    public String guid;

    @Element(required = true)
    public Enclosure enclosure;


}

