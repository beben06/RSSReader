package com.dandine.benjamin.rssreader.rssfeed;

import com.dandine.benjamin.rssreader.data.source.model.Item;
import com.dandine.benjamin.rssreader.data.source.model.RSSFeed;

/**
 * Created by benjamindandine on 20/08/2017.
 */

public interface RSSFeedViewPresenter {


    interface View {

        void displayRSS(RSSFeed rssFeed);

        void displayErrorRSS();

        void displayNoData();

        void displayDetail(Item item);
    }

    interface Presenter {

        void loadFeed();

        void requestDetail(Item item);

    }

}
