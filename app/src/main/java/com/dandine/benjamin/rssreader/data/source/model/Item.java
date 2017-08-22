package com.dandine.benjamin.rssreader.data.source.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by benjamindandine on 07/02/2017.
 */
@Root(strict = false)
public class Item implements Parcelable {

    public static final String ITEM = "item";
    public static final String ITEM_TITLE = "item_title";
    public static final String ITEM_LINK = "item_link";
    public static final String ITEM_DESCRIPTION = "item_description";
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
    @Element(name = "title")
    public String title;
    @Element(name = "link")
    public String link;
    @Element(name = "description")
    public String description;
    @Element(name = "pubDate")
    public String pubDate;
    @Element(name = "guid")
    public String guid;
    @Element(required = true)
    public Enclosure enclosure;

    public Item() {
    }

    protected Item(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.description = in.readString();
        this.pubDate = in.readString();
        this.guid = in.readString();
        this.enclosure = in.readParcelable(Enclosure.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.description);
        dest.writeString(this.pubDate);
        dest.writeString(this.guid);
        dest.writeParcelable(this.enclosure, flags);
    }
}

