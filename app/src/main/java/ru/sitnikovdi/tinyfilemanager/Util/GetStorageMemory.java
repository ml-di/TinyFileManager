package ru.sitnikovdi.tinyfilemanager.Util;

import android.content.Context;
import androidx.core.content.ContextCompat;
import java.io.File;

public class GetStorageMemory {

    public static boolean isSDCardAvailable(Context context) {
        final File[] storage = ContextCompat.getExternalFilesDirs(context, null);
        return storage.length > 1 && storage[0] != null && storage[1] != null;
    }

    public static long getTotalSpace(int type, Context context) {
        final File[] storage = ContextCompat.getExternalFilesDirs(context, null);
        return storage[type].getTotalSpace();
    }

    public static long getFreeSpace(int type, Context context) {

        final File[] storage = ContextCompat.getExternalFilesDirs(context, null);
        return storage[type].getUsableSpace();
    }

}
