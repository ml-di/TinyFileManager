package ru.sitnikovdi.tinyfilemanager.Util;

import android.content.Context;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FilesManager {

    public static ArrayList<File> getFileList(Context context, String path) {
        return getList(context, -1, path);
    }

    public static ArrayList<File> getFileList(Context context, int id) {
        return getList(context, id, null);
    }

    public static String getPathStorage(Context context, int type) {
        final File[] storage = ContextCompat.getExternalFilesDirs(context, null);
        return storage[type].getParentFile().getParentFile().getParentFile().getParentFile().getPath();
    }

    private static ArrayList<File> getList(Context context, int id, String path) {
        if (id > -1 && path == null) {
            final File[] storage = ContextCompat.getExternalFilesDirs(context, null);
            final File[] files = storage[id].getParentFile().getParentFile().getParentFile().getParentFile().listFiles();
            return new ArrayList<>(Arrays.asList(files));
        } else if (id == -1 && path != null) {
            final File files = new File(path);
            return new ArrayList<>(Arrays.asList(files.listFiles()));
        } else return null;
    }
}
