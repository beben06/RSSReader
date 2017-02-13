package com.dandine.benjamin.rssreader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dandine.benjamin.rssreader.model.Enclosure;
import com.dandine.benjamin.rssreader.model.Item;
import com.squareup.picasso.Picasso;

/**
 * Detail Activity
 * Display the details of an article
 */
public class DetailActivity extends AppCompatActivity {

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
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString(Item.SHARED_PREFERENCE_ITEM_TITLE, null);
        String description = bundle.getString(Item.SHARED_PREFERENCE_ITEM_DESCRIPTION, null);
        final String link = bundle.getString(Item.SHARED_PREFERENCE_ITEM_LINK, null);
        String urlImage = bundle.getString(Enclosure.SHARED_PREFERENCE_ITEM_ENCLOSURE_URL, null);

        TextView textViewTitle = (TextView) findViewById(R.id.title);
        textViewTitle.setText(title);

        TextView textViewDescription = (TextView) findViewById(R.id.description);
        textViewDescription.setText(description);

        //To avoid a new network call, an alternative could be to store the data received previously
        ImageView imageView = (ImageView) findViewById(R.id.image);
        if (urlImage != null) {
            Picasso.with(this).load(urlImage).into(imageView);
        }

        Button button = (Button) findViewById(R.id.button);
        //Click on the button to read more
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Open a the link in a browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(browserIntent);
            }
        });

    }
}
