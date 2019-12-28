package ru.sitnikovdi.tinyfilemanager.Data.Files;

import android.os.Parcel;
import android.os.Parcelable;

public class RecyclerViewFilesOtherFileData implements Parcelable {

    private final String path;
    private final String title;
    private final long editTime;
    private final long size;

    public RecyclerViewFilesOtherFileData(final String path,
                                          final String title,
                                          final long editTime,
                                          final long size) {
        this.path = path;
        this.title = title;
        this.editTime = editTime;
        this.size = size;
    }

    private RecyclerViewFilesOtherFileData(Parcel in) {
        path = in.readString();
        title = in.readString();
        editTime = in.readLong();
        size = in.readLong();
    }

    public static final Parcelable.Creator<RecyclerViewFilesOtherFileData> CREATOR = new Parcelable.Creator<RecyclerViewFilesOtherFileData>() {
        @Override
        public RecyclerViewFilesOtherFileData createFromParcel(Parcel in) {
            return new RecyclerViewFilesOtherFileData(in);
        }

        @Override
        public RecyclerViewFilesOtherFileData[] newArray(int size) {
            return new RecyclerViewFilesOtherFileData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeString(title);
        parcel.writeLong(editTime);
        parcel.writeLong(size);
    }

    public String getPath() {
        return path;
    }
    public String getTitle() {
        return title;
    }
    public long getEditTime() {
        return editTime;
    }
    public long getSize() {
        return size;
    }
}
