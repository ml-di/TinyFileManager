package ru.sitnikovdi.tinyfilemanager.Data.Files;

import android.os.Parcel;
import android.os.Parcelable;

public class RecyclerViewFilesFolderData implements Parcelable {

    private final String path;
    private final String title;
    private final int filesCount;

    public RecyclerViewFilesFolderData(final String path,
                                       final String title,
                                       final int filesCount) {
        this.path = path;
        this.title = title;
        this.filesCount = filesCount;
    }

    private RecyclerViewFilesFolderData (Parcel in) {
        path = in.readString();
        title = in.readString();
        filesCount = in.readInt();
    }

    public static final Parcelable.Creator<RecyclerViewFilesFolderData> CREATOR = new Parcelable.Creator<RecyclerViewFilesFolderData>() {
        @Override
        public RecyclerViewFilesFolderData createFromParcel(Parcel in) {
            return new RecyclerViewFilesFolderData(in);
        }

        @Override
        public RecyclerViewFilesFolderData[] newArray(int size) {
            return new RecyclerViewFilesFolderData[size];
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
        parcel.writeInt(filesCount);
    }

    public String getPath() {
        return path;
    }
    public String getTitle() {
        return title;
    }
    public int getFilesCount() {
        return filesCount;
    }
}
