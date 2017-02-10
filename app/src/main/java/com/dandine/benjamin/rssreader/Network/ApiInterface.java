package com.dandine.benjamin.rssreader.Network;

import com.dandine.benjamin.rssreader.Model.RSS;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by benjamindandine on 03/08/2016.
 */
public interface ApiInterface {

    @GET("/rss/une.xml")
    Call<RSS> getRSS();

}
