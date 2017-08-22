package com.dandine.benjamin.rssreader.data.source.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by benjamindandine on 07/02/2017.
 */
@Root(strict = false)
public class Enclosure implements Parcelable {

    public static final String ITEM_ENCLOSURE_URL = "item_enclosure_url";
    public static final Parcelable.Creator<Enclosure> CREATOR = new Parcelable.Creator<Enclosure>() {
        @Override
        public Enclosure createFromParcel(Parcel source) {
            return new Enclosure(source);
        }

        @Override
        public Enclosure[] newArray(int size) {
            return new Enclosure[size];
        }
    };
    @Attribute(name = "url")
    private String url;

    public Enclosure() {
    }


    protected Enclosure(Parcel in) {
        this.url = in.readString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
    }
}
