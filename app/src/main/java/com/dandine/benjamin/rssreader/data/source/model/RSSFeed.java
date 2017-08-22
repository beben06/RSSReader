package com.dandine.benjamin.rssreader.data.source.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by benjamindandine on 07/02/2017.
 */

@Root(strict = false)
public class RSSFeed {

    @Element(required = false)
    public Channel channel;

}
