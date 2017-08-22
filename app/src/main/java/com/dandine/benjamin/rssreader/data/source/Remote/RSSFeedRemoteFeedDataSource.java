package com.dandine.benjamin.rssreader.data.source.Remote;

import com.dandine.benjamin.rssreader.data.source.RSSFeedDataSource;
import com.dandine.benjamin.rssreader.data.source.model.RSSFeed;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by benjamindandine on 20/08/2017.
 */

public class RSSFeedRemoteFeedDataSource implements RSSFeedDataSource {

    ApiInterface apiService;

    public RSSFeedRemoteFeedDataSource() {
        if (apiService == null) {
            this.apiService = ApiClient.getClient().create(ApiInterface.class);
        }
    }

    @Override
    public void getRSS(final GetRSSFeedCallback rssCallback) {
        //Get feed RSSFeed
        Observable<RSSFeed> observableRSS = apiService.getRSSFeed();
        observableRSS.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RSSFeed>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RSSFeed rssFeed) {
                        rssCallback.onSuccess(rssFeed);

                    }

                    @Override
                    public void onError(Throwable e) {
                        rssCallback.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
