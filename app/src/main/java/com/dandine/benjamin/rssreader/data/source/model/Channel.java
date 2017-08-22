package com.dandine.benjamin.rssreader.data.source.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by benjamindandine on 07/02/2017.
 */
@Root(strict = false)
public class Channel {

    @ElementList(entry = "item", inline = true, required = false)
    public List<Item> items;

}
