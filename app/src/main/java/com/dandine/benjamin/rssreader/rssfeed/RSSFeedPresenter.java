package com.dandine.benjamin.rssreader.rssfeed;

import com.dandine.benjamin.rssreader.data.source.RSSFeedDataSource;
import com.dandine.benjamin.rssreader.data.source.Remote.RSSFeedRemoteFeedDataSource;
import com.dandine.benjamin.rssreader.data.source.model.Item;
import com.dandine.benjamin.rssreader.data.source.model.RSSFeed;

/**
 * Created by benjamindandine on 20/08/2017.
 */

public class RSSFeedPresenter implements RSSFeedViewPresenter.Presenter {


    private final RSSFeedViewPresenter.View view;
    private final RSSFeedRemoteFeedDataSource rssFeedRemoteDataSource;

    public RSSFeedPresenter(RSSFeedViewPresenter.View view, RSSFeedRemoteFeedDataSource rssFeedRemoteDataSource) {
        this.view = view;
        this.rssFeedRemoteDataSource = rssFeedRemoteDataSource;
    }

    @Override
    public void loadFeed() {
        rssFeedRemoteDataSource.getRSS(new RSSFeedDataSource.GetRSSFeedCallback() {
            @Override
            public void onSuccess(RSSFeed rssFeedList) {
                view.displayRSS(rssFeedList);
            }

            @Override
            public void onFailure() {
                view.displayNoData();
            }

            @Override
            public void onError() {
                view.displayErrorRSS();
            }
        });
    }

    @Override
    public void requestDetail(Item item) {
        view.displayDetail(item);
    }
}
