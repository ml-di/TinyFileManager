package ru.sitnikovdi.tinyfilemanager.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class RecyclerViewStorageMainData implements Parcelable {

    private final int type;
    private final float memory;
    private final float freeMemory;
    private final int iconRes;

    public RecyclerViewStorageMainData(final int type,
                                       final float memory,
                                       final float freeMemory,
                                       final int iconRes) {
        this.type = type;
        this.memory = memory;
        this.freeMemory = freeMemory;
        this.iconRes = iconRes;
    }

    private RecyclerViewStorageMainData(Parcel in) {
        type = in.readInt();
        memory = in.readFloat();
        freeMemory = in.readFloat();
        iconRes = in.readInt();
    }

    public static final Creator<RecyclerViewStorageMainData> CREATOR = new Creator<RecyclerViewStorageMainData>() {
        @Override
        public RecyclerViewStorageMainData createFromParcel(Parcel in) {
            return new RecyclerViewStorageMainData(in);
        }

        @Override
        public RecyclerViewStorageMainData[] newArray(int size) {
            return new RecyclerViewStorageMainData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(type);
        parcel.writeFloat(memory);
        parcel.writeFloat(freeMemory);
        parcel.writeInt(iconRes);
    }

    public int getType() {
        return type;
    }
    public float getMemory() {
        return memory;
    }
    public float getFreeMemory() {
        return freeMemory;
    }
    public int getIconRes() {
        return iconRes;
    }
}
