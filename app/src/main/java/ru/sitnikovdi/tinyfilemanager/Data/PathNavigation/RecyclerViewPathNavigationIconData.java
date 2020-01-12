package ru.sitnikovdi.tinyfilemanager.Data.PathNavigation;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;

public class RecyclerViewPathNavigationIconData implements Parcelable {

    @DrawableRes private final int iconRes;
    private final String path;

    public RecyclerViewPathNavigationIconData(@DrawableRes final int iconRes,
                                              final String path) {
        this.iconRes = iconRes;
        this.path = path;
    }

    private RecyclerViewPathNavigationIconData(Parcel in) {
        iconRes = in.readInt();
        path = in.readString();
    }

    public static final Parcelable.Creator<RecyclerViewPathNavigationIconData> CREATOR = new Parcelable.Creator<RecyclerViewPathNavigationIconData>() {
        @Override
        public RecyclerViewPathNavigationIconData createFromParcel(Parcel in) {
            return new RecyclerViewPathNavigationIconData(in);
        }

        @Override
        public RecyclerViewPathNavigationIconData[] newArray(int size) {
            return new RecyclerViewPathNavigationIconData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(iconRes);
        parcel.writeString(path);
    }

    public int getIconRes() {
        return iconRes;
    }
    public String getPath() {
        return path;
    }
}
