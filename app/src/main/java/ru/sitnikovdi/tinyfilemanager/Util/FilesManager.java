package ru.sitnikovdi.tinyfilemanager.Util;

import android.content.Context;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FilesManager {

    public static ArrayList<File> getFileList(Context context, int id) {
        final File[] storage = ContextCompat.getExternalFilesDirs(context, null);
        final File[] files = storage[id].getParentFile().getParentFile().getParentFile().getParentFile().listFiles();
        return new ArrayList<>(Arrays.asList(files));
    }

}
