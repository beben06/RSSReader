package com.dandine.benjamin.rssreader.data.source;

import com.dandine.benjamin.rssreader.data.source.model.RSSFeed;

/**
 * Created by benjamindandine on 20/08/2017.
 */

public interface RSSFeedDataSource {

    void getRSS(GetRSSFeedCallback rssCallback);

    interface GetRSSFeedCallback {

        void onSuccess(RSSFeed rssFeedList);

        void onFailure();

        void onError();

    }
}
