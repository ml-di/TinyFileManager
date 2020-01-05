package ru.sitnikovdi.tinyfilemanager.Util;

import java.io.File;

public class FileNameHelper {

    public static String getExt(String fullFileName) {
        return ext(new File(fullFileName));
    }

    public static String getExt(File file) {
        return ext(file);
    }

    private static String ext(File file) {
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }

    public static String getName(String fullFileName) {
        final File tempFile = new File(fullFileName);
        return name(tempFile);
    }

    public static String getName(File file) {
        return name(file);
    }

    private static String name(File file) {
        return (file.getName().contains("."))
                ? file.getName().substring(0, file.getName().lastIndexOf("."))
                : file.getName();
    }

}
