package com.dandine.benjamin.rssreader.data.source.Remote;

import com.dandine.benjamin.rssreader.data.source.model.RSSFeed;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by benjamindandine on 03/08/2016.
 */
public interface ApiInterface {

    @GET("/rss/une.xml")
    Observable<RSSFeed> getRSSFeed();

}
