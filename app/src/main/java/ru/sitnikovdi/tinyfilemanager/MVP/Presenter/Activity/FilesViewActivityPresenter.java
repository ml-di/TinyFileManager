package ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity;

import android.os.Parcelable;
import java.io.File;
import java.util.ArrayList;
import ru.sitnikovdi.tinyfilemanager.Const.SortListType;
import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesFolderData;
import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesOtherFileData;
import ru.sitnikovdi.tinyfilemanager.MVP.BasePresenter;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.FilesViewActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.FilesViewActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.Util.FilesManager;
import ru.sitnikovdi.tinyfilemanager.Util.ListHelper;

public class FilesViewActivityPresenter extends BasePresenter<FilesViewActivityViewInterface> implements FilesViewActivityPresenterInterface {

    @Override
    public void viewIsReady() {
        getView().initFilesRecyclerView(R.id.files_view_recyclerview);
        getView().initFilesRecyclerViewAdapter();
        getView().initAppBar(R.id.files_view_appbar);
        getView().initAppBarTitle(R.id.files_view_appbar_title);
        getView().initAppBarSelectAllBtn(R.id.files_view_btn_selectAll);
        getView().initAppBarSortBtn(R.id.files_view_btn_sort);
        getView().initAppBarMenuBtn(R.id.files_view_btn_menu);
    }

    @Override
    public void fileAdapterClick(String path, String name) {
        updateList(path, name);
    }

    @Override
    public void updateList(String path, String name) {
        final ArrayList<Parcelable> mList = ListHelper.getSortList(getFilesArrayList(path), SortListType.SORT_NAME);
        getView().updateRecyclerViewAdapter(mList);
        getView().setAppBarTitleText(name);
        getView().setCurrentPath(path);
        getView().expandAppBar(true, false);
    }

    @Override
    public ArrayList<Parcelable> getFilesArrayList(String path) {
        final ArrayList<Parcelable> files = new ArrayList<>();
        final ArrayList<File> filesArray =
                (path == null)
                        ? FilesManager.getFileList(getView().getContext(), getView().getTypeStorage())
                        : FilesManager.getFileList(getView().getContext(), path);

        for (File f : filesArray) {
            if (f.isDirectory()) {
                files.add(new RecyclerViewFilesFolderData(f.getPath(), f.getName(), f.listFiles().length, f.isHidden()));
            } else {
                files.add(new RecyclerViewFilesOtherFileData(f.getPath(), f.getName(), f.lastModified(), f.length(), f.isHidden()));
            }
        }
        return files;
    }
}
