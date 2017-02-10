package com.dandine.benjamin.rssreader.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by benjamindandine on 07/02/2017.
 */

@Root(strict = false)
public class RSS {

    @Element(required = false)
    public Channel channel;

}
