package com.dandine.benjamin.rssreader.rssfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dandine.benjamin.rssreader.R;
import com.dandine.benjamin.rssreader.adapter.RecyclerViewAdapter;
import com.dandine.benjamin.rssreader.data.source.Remote.RSSFeedRemoteFeedDataSource;
import com.dandine.benjamin.rssreader.data.source.model.Item;
import com.dandine.benjamin.rssreader.data.source.model.RSSFeed;
import com.dandine.benjamin.rssreader.item.ItemDetailActivity;

/**
 * Created by benjamindandine on 20/08/2017.
 */

public class RSSFeedActivity extends AppCompatActivity implements RSSFeedViewPresenter.View, RecyclerViewAdapter.OnItemClickListener {

    private static final String TAG = RSSFeedActivity.class.getName();
    RSSFeedPresenter rssFeedPresenter;
    private RecyclerViewAdapter.OnItemClickListener onItemClickListener;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_rss_activity);

        //Interface to manage click on the items of the recyclerView
        onItemClickListener = this;

        //RecyclerView binding
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);

        //doesn't change recyclerView's height or width
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        rssFeedPresenter = new RSSFeedPresenter(this, new RSSFeedRemoteFeedDataSource());
        rssFeedPresenter.loadFeed();
    }


    @Override
    public void displayRSS(RSSFeed rssFeed) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(rssFeed.channel.items, onItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayErrorRSS() {
        Toast.makeText(getApplicationContext(), getString(R.string.error_access_rss), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayNoData() {
        Toast.makeText(getApplicationContext(), getString(R.string.error_network), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayDetail(Item item) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra(Item.ITEM, item);
        startActivity(intent);
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
        rssFeedPresenter.requestDetail(item);
    }
}
