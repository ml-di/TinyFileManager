package ru.sitnikovdi.tinyfilemanager.Data.PathNavigation;

import android.os.Parcel;
import android.os.Parcelable;

public class RecyclerViewPathNavigationTitleData implements Parcelable {

    private final String title;

    public RecyclerViewPathNavigationTitleData(final String title) {
        this.title = title;
    }

    private RecyclerViewPathNavigationTitleData(Parcel in) {
        title = in.readString();
    }

    public static final Parcelable.Creator<RecyclerViewPathNavigationTitleData> CREATOR = new Parcelable.Creator<RecyclerViewPathNavigationTitleData>() {
        @Override
        public RecyclerViewPathNavigationTitleData createFromParcel(Parcel in) {
            return new RecyclerViewPathNavigationTitleData(in);
        }

        @Override
        public RecyclerViewPathNavigationTitleData[] newArray(int size) {
            return new RecyclerViewPathNavigationTitleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
    }

    public String getTitle() {
        return title;
    }
}
