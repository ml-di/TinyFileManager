package ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity;

import android.os.Parcelable;

import java.io.File;
import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesFolderData;
import ru.sitnikovdi.tinyfilemanager.MVP.BasePresenter;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.FilesViewActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.FilesViewActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.Util.FilesManager;

public class FilesViewActivityPresenter extends BasePresenter<FilesViewActivityViewInterface> implements FilesViewActivityPresenterInterface {

    @Override
    public void viewIsReady() {
        getView().initFilesRecyclerView(R.id.files_view_recyclerview);
        getView().initFilesRecyclerViewAdapter();
    }

    @Override
    public void fileAdapterClick(File file) {

    }

    @Override
    public ArrayList<Parcelable> getFilesArrayList() {
        final ArrayList<Parcelable> files = new ArrayList<>();
        for (File f :FilesManager.getFileList(getView().getContext(), getView().getTypeStorage())) {
            if (f.isDirectory()) {
                files.add(new RecyclerViewFilesFolderData(f.getPath(), f.getName(), 0));
            }
        }
        return files;
    }
}
