package com.dandine.benjamin.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dandine.benjamin.rssreader.adapter.RecyclerViewAdapter;
import com.dandine.benjamin.rssreader.model.Item;
import com.dandine.benjamin.rssreader.model.RSS;
import com.dandine.benjamin.rssreader.network.ApiClient;
import com.dandine.benjamin.rssreader.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Main Activity
 * Display the list of articles in the recyclerView and get the RSS feed
 */
public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {

    //Base url of the website to fetch the RSS
    public static final String BASE_URL = " http://www.lemonde.fr";

    RecyclerView recyclerView;
    RecyclerViewAdapter.OnItemClickListener onItemClickListener;

    /**
     * OnCreate of the activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Interface to manage click on the items of the recyclerView
        onItemClickListener = this;

        //RecyclerView binding
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);

        //doesn't change recyclerView's height or width
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        //Get feed RSS
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RSS> call = apiService.getRSS();
        call.enqueue(new Callback<RSS>() {

            @Override
            public void onResponse(Call<RSS> call, Response<RSS> response) {
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
            public void onFailure(Call<RSS> call, Throwable t) {
                // something went completely south (like no internet connection)
                Toast.makeText(getApplicationContext(), getString(R.string.error_network), Toast.LENGTH_SHORT).show();
            }
        });
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
        intent.putExtra("Item", item);
        startActivity(intent);
    }
}
