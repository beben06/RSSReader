package com.dandine.benjamin.rssreader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dandine.benjamin.rssreader.model.Item;
import com.squareup.picasso.Picasso;

/**
 * Detail Activity
 * Display the details of an article
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    Item item;

    /**
     * On Create of the activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Get data from intent
        item = (Item) getIntent().getExtras().getSerializable("Item");

        TextView textViewTitle = (TextView) findViewById(R.id.title);
        textViewTitle.setText(item.title);

        TextView textViewDescription = (TextView) findViewById(R.id.description);
        textViewDescription.setText(item.description);

        //To avoid a new network call, an alternative could be to store the data received previously
        ImageView imageView = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(item.enclosure.getUrl()).into(imageView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    /**
     * Click on the button to read more
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        finish();
        //Open a the link in a browser
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.link));
        startActivity(browserIntent);
    }
}
