package com.dandine.benjamin.rssreader.item;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dandine.benjamin.rssreader.R;
import com.dandine.benjamin.rssreader.data.source.model.Item;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Detail Activity
 * Display the details of an article
 */
public class ItemDetailActivity extends AppCompatActivity implements ItemDetailViewPresenter.View {


    ItemDetailPresenter itemDetailPresenter;

    @BindView(R.id.title)
    TextView textViewTitle;

    @BindView(R.id.description)
    TextView textViewDescription;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.button)
    Button button;

    /**
     * On Create of the activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        //Get data from intent
        Bundle bundle = getIntent().getExtras();

        textViewTitle = (TextView) findViewById(R.id.title);

        itemDetailPresenter = new ItemDetailPresenter(this);
        itemDetailPresenter.loadItemDetail(bundle.getParcelable(Item.ITEM));

        //Click on the button to read more
        button.setOnClickListener(v -> itemDetailPresenter.clickButton());

    }


    @Override
    public void displayTitle(String title) {
        textViewTitle.setText(title);
    }

    @Override
    public void displayDescription(String description) {
        textViewDescription.setText(description);
    }

    @Override
    public void openLink(String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    @Override
    public void displayUrlImage(String urlImage) {
        RequestCreator load = Picasso.with(this).load(urlImage);
        if (load != null) load.into(imageView);
    }

    @Override
    public void closeDueToError() {
        finish();
    }
}
