package ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity;

import android.content.Context;
import android.os.Parcelable;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.FilesViewActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPView;

public interface FilesViewActivityViewInterface extends MVPView {

    void initFilesRecyclerView(int resId);
    void initFilesRecyclerViewAdapter();

    int getTypeStorage();

    void updateRecyclerViewAdapter(ArrayList<Parcelable> list);

    FilesViewActivityPresenterInterface getPresenter ();
    Context getContext ();
}
