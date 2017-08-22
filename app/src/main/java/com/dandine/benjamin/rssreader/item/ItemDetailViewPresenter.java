package com.dandine.benjamin.rssreader.item;

import com.dandine.benjamin.rssreader.data.source.model.Item;

/**
 * Created by benjamindandine on 21/08/2017.
 */

public interface ItemDetailViewPresenter {

    interface View {

        void displayTitle(String title);

        void displayDescription(String description);

        void openLink(String link);

        void displayUrlImage(String urlImage);

        void closeDueToError();

    }

    interface Presenter {

        void loadItemDetail(Item item);

        void clickButton();
    }

}
