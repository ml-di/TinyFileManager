package ru.sitnikovdi.tinyfilemanager.Util;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ru.sitnikovdi.tinyfilemanager.Const.SortListType;
import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesFolderData;
import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesOtherFileData;

public class ListHelper {

    public static ArrayList<Parcelable> getSortList(ArrayList<Parcelable> mList, int sortListType) {
        if (sortListType == SortListType.SORT_NAME) {
            Collections.sort(mList, new SortName());
        }
        return folderFirstSort(mList);
    }

    public static ArrayList<Parcelable> reverseList(ArrayList<Parcelable> mList) {

        return null;
    }

    private static ArrayList<Parcelable> folderFirstSort(ArrayList<Parcelable> mList) {

        final ArrayList<Parcelable> folderList = new ArrayList<>();
        final ArrayList<Parcelable> otherFileList = new ArrayList<>();
        for (Parcelable p : mList) {
            if (p instanceof RecyclerViewFilesFolderData) {
                folderList.add(p);
            } else {
                otherFileList.add(p);
            }
        }
        folderList.addAll(otherFileList);
        return folderList;
    }

    private static class SortName implements Comparator<Parcelable> {
        @Override
        public int compare(Parcelable o1, Parcelable o2) {

            String name1 = null;
            String name2 = null;

            if (o1 instanceof RecyclerViewFilesFolderData) {
                name1 = ((RecyclerViewFilesFolderData) o1).getTitle();
            } else if (o1 instanceof RecyclerViewFilesOtherFileData) {
                name1 = ((RecyclerViewFilesOtherFileData) o1).getTitle();
            }

            if (o2 instanceof RecyclerViewFilesFolderData) {
                name2 = ((RecyclerViewFilesFolderData) o2).getTitle();
            } else if (o2 instanceof RecyclerViewFilesOtherFileData) {
                name2 = ((RecyclerViewFilesOtherFileData) o2).getTitle();
            }

            if (name1 != null && name2 != null) {
                return name1.compareToIgnoreCase(name2);
            }

            return 0;
        }
    }
}
