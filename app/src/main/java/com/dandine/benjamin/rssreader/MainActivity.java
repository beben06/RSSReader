package com.dandine.benjamin.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dandine.benjamin.rssreader.adapter.RecyclerViewAdapter;
import com.dandine.benjamin.rssreader.model.Enclosure;
import com.dandine.benjamin.rssreader.model.Item;
import com.dandine.benjamin.rssreader.model.RSS;
import com.dandine.benjamin.rssreader.network.ApiClient;
import com.dandine.benjamin.rssreader.network.ApiInterface;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Main Activity
 * Display the list of articles in the recyclerView and get the RSS feed
 */
public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {

    //Base url of the website to fetch the RSS
    public static final String BASE_URL = " http://www.lemonde.fr";
    CompositeDisposable compositeDisposable;
    private RecyclerViewAdapter.OnItemClickListener onItemClickListener;
    private RecyclerView recyclerView;

    /**
     * OnCreate of the activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayout();
        createObservable();

    }

    public void createObservable() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //Get feed RSS
        Observable<Response<RSS>> observableRSS = apiService.getRSS();
        observableRSS.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<RSS>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<RSS> response) {

                        if (response.isSuccessful()) {
                            //Populate adapter of the recycler view
                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(response.body().channel.items, onItemClickListener);
                            recyclerView.setAdapter(adapter);
                        } else {
                            //An error from server has been received
                            Toast.makeText(getApplicationContext(), getString(R.string.error_access_rss), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), getString(R.string.error_network), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void initializeLayout() {

        //Interface to manage click on the items of the recyclerView
        onItemClickListener = this;

        //RecyclerView binding
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);

        //doesn't change recyclerView's height or width
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }

    /**
     * Clic on the recyclerView
     *
     * @param view
     * @param item
     */
    @Override
    public void onItemClick(View view, Item item) {
        //Display detail of the item in a dedicated activity
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Item.SHARED_PREFERENCE_ITEM_TITLE, item.title);
        intent.putExtra(Item.SHARED_PREFERENCE_ITEM_DESCRIPTION, item.description);
        intent.putExtra(Item.SHARED_PREFERENCE_ITEM_LINK, item.link);
        intent.putExtra(Enclosure.SHARED_PREFERENCE_ITEM_ENCLOSURE_URL, item.enclosure != null ? item.enclosure.getUrl() : null);
        startActivity(intent);
    }

}
