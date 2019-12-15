package ru.sitnikovdi.tinyfilemanager.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class RecyclerViewCategoriesMainData implements Parcelable {

    private final String type;
    private final String title;
    private final int iconRes;
    private final int colorRes;

    public RecyclerViewCategoriesMainData(final String type,
                                          final String title,
                                          final int iconRes,
                                          final int colorRes) {
        this.type = type;
        this.title = title;
        this.iconRes = iconRes;
        this.colorRes = colorRes;
    }

    private RecyclerViewCategoriesMainData(Parcel in) {
        type = in.readString();
        title = in.readString();
        iconRes = in.readInt();
        colorRes = in.readInt();
    }

    public static final Parcelable.Creator<RecyclerViewCategoriesMainData> CREATOR = new Parcelable.Creator<RecyclerViewCategoriesMainData>() {
        @Override
        public RecyclerViewCategoriesMainData createFromParcel(Parcel in) {
            return new RecyclerViewCategoriesMainData(in);
        }

        @Override
        public RecyclerViewCategoriesMainData[] newArray(int size) {
            return new RecyclerViewCategoriesMainData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(title);
        parcel.writeInt(iconRes);
        parcel.writeInt(colorRes);
    }

    public String getType() {
        return type;
    }
    public String getTitle() {
        return title;
    }
    public int getIconRes() {
        return iconRes;
    }
    public int getColorRes() {
        return colorRes;
    }
}
