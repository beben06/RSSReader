package com.dandine.benjamin.rssreader;

import com.dandine.benjamin.rssreader.data.source.Remote.RSSFeedRemoteFeedDataSource;
import com.dandine.benjamin.rssreader.data.source.model.Item;
import com.dandine.benjamin.rssreader.rssfeed.RSSFeedActivity;
import com.dandine.benjamin.rssreader.rssfeed.RSSFeedPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by benjamindandine on 20/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RSSFeedReaderPresenterTest {

    @Mock
    RSSFeedActivity view;
    @Mock
    RSSFeedRemoteFeedDataSource remoteDataSource;
    @Mock
    Item item;

    RSSFeedPresenter rssFeedPresenter;

    @Before
    public void setUp() {
        rssFeedPresenter = new RSSFeedPresenter(view, remoteDataSource);
    }

    @Test
    public void shouldDisplayDetail() {
        rssFeedPresenter.requestDetail(item);
        Mockito.verify(view).displayDetail(item);
    }

    @Test
    public void shouldPassRSSToView() {

/*
        Mockito.when(remoteDataSource.getRSSFeed(new RSSFeedDataSource.GetRSSFeedCallback() {
            @Override
            public void onSuccess(RSSFeed rssList) {
                then
            }

            @Override
            public void onFailure() {

            }

            @Override
            public void onError() {

            }
        }))

        RSSFeedPresenter rssPresenter = new RSSFeedPresenter(view, repository);
        rssPresenter.loadFeed();
*/


    }

}
