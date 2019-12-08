package ru.sitnikovdi.tinyfilemanager.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class RecyclerViewStorageMainData implements Parcelable {

    private final int type;
    private final long memory;
    private final long freeMemory;
    private final int iconRes;

    public RecyclerViewStorageMainData(final int type,
                                       final long memory,
                                       final long freeMemory,
                                       final int iconRes) {
        this.type = type;
        this.memory = memory;
        this.freeMemory = freeMemory;
        this.iconRes = iconRes;
    }

    private RecyclerViewStorageMainData(Parcel in) {
        type = in.readInt();
        memory = in.readLong();
        freeMemory = in.readLong();
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
        parcel.writeLong(memory);
        parcel.writeLong(freeMemory);
        parcel.writeInt(iconRes);
    }

    public int getType() {
        return type;
    }
    public long getMemory() {
        return memory;
    }
    public long getFreeMemory() {
        return freeMemory;
    }
    public int getIconRes() {
        return iconRes;
    }
}
