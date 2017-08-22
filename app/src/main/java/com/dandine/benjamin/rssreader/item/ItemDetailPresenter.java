package com.dandine.benjamin.rssreader.item;

import com.dandine.benjamin.rssreader.data.source.model.Item;

/**
 * Created by benjamindandine on 21/08/2017.
 */

public class ItemDetailPresenter implements ItemDetailViewPresenter.Presenter {

    private ItemDetailActivity view;

    private Item item;

    public ItemDetailPresenter(ItemDetailActivity view) {
        this.view = view;
    }

    @Override
    public void loadItemDetail(Item _item) {
        item = _item;
        displayItem();
    }

    public void displayItem() {
        if (item != null) {
            view.displayTitle(item.title);
            view.displayDescription(item.description);
            if (item.enclosure != null) {
                view.displayUrlImage(item.enclosure.getUrl());
            }
        } else {
            view.closeDueToError();
        }
    }

    @Override
    public void clickButton() {
        if (item != null) {
            view.openLink(item.link);
        } else {
            view.closeDueToError();
        }
    }
}
