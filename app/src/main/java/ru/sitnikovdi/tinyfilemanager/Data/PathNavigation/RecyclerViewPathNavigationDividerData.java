package ru.sitnikovdi.tinyfilemanager.Data.PathNavigation;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

public class RecyclerViewPathNavigationDividerData implements Parcelable {

    @DrawableRes private final int dividerIconRes;

    public RecyclerViewPathNavigationDividerData(@DrawableRes final int dividerIconRes) {
        this.dividerIconRes = dividerIconRes;
    }

    private RecyclerViewPathNavigationDividerData(Parcel in) {
        dividerIconRes = in.readInt();
    }

    public static final Parcelable.Creator<RecyclerViewPathNavigationDividerData> CREATOR = new Parcelable.Creator<RecyclerViewPathNavigationDividerData>() {
        @Override
        public RecyclerViewPathNavigationDividerData createFromParcel(Parcel in) {
            return new RecyclerViewPathNavigationDividerData(in);
        }

        @Override
        public RecyclerViewPathNavigationDividerData[] newArray(int size) {
            return new RecyclerViewPathNavigationDividerData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(dividerIconRes);
    }

    public int getDividerIconRes() {
        return dividerIconRes;
    }
}
