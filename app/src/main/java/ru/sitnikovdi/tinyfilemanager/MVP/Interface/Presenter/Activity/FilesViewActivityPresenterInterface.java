package ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity;

import android.os.Parcelable;

import java.io.File;
import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.FilesViewActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPPresenter;

public interface FilesViewActivityPresenterInterface extends MVPPresenter<FilesViewActivityViewInterface> {

    void fileAdapterClick(String path, String name);

    ArrayList<Parcelable> getFilesArrayList(String path);
}
