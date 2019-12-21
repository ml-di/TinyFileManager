package ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity;

import android.content.Context;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.FilesViewActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPView;

public interface FilesViewActivityViewInterface extends MVPView {

    void initFilesRecyclerView(int resId);
    void initFilesRecyclerViewAdapter();

    int getTypeStorage();

    FilesViewActivityPresenterInterface getPresenter ();
    Context getContext ();
}
