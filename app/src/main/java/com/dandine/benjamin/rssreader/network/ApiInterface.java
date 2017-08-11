package com.dandine.benjamin.rssreader.network;

import com.dandine.benjamin.rssreader.model.RSS;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by benjamindandine on 03/08/2016.
 */
public interface ApiInterface {

    @GET("/rss/une.xml")
    Observable<Response<RSS>> getRSS();

}
