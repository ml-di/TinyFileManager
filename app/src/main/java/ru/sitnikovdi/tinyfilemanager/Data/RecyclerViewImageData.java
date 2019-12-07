package ru.sitnikovdi.tinyfilemanager.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class RecyclerViewImageData implements Parcelable {

    private final int imageRes;

    public RecyclerViewImageData(final int imageRes) {
        this.imageRes = imageRes;
    }

    private RecyclerViewImageData(Parcel in) {
        imageRes = in.readInt();
    }

    public static final Creator<RecyclerViewImageData> CREATOR = new Creator<RecyclerViewImageData>() {
        @Override
        public RecyclerViewImageData createFromParcel(Parcel in) {
            return new RecyclerViewImageData(in);
        }

        @Override
        public RecyclerViewImageData[] newArray(int size) {
            return new RecyclerViewImageData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageRes);
    }

    public int getImageRes() {
        return imageRes;
    }
}
