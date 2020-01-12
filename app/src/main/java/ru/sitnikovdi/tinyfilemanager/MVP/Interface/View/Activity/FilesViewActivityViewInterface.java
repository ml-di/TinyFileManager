package ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity;

import android.content.Context;
import android.os.Parcelable;
import java.util.ArrayList;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.FilesViewActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPView;

public interface FilesViewActivityViewInterface extends MVPView {

    void initFilesRecyclerView(int resId);
    void initPathNavigationRecyclerView(int resId);
    void initAppBar(int resId);
    void initAppBarTitle(int resId);
    void initAppBarSelectAllBtn(int resId);
    void initAppBarSortBtn(int resId);
    void initAppBarMenuBtn(int resId);
    void initProgressBar(int resId);
    void initCollapsingToolbarLayout(int resId);

    void initFilesRecyclerViewAdapter();
    void initPathNavigationRecyclerViewAdapter();

    void expandAppBar(boolean isExpand, boolean isAnimation);
    void updateRecyclerViewAdapter(ArrayList<Parcelable> list);
    void clearRecyclerViewAdapter();

    void showRecyclerView();
    void hideRecyclerView();
    void showProgressBar();
    void hideProgressBar();

    void updatePathNavigationItems(String path);
    void resetScrollFlag();

    void setAppBarTitleText(String str);
    void setCurrentPath(String path);

    int getTypeStorage();
    String getCurrentPath();
    FilesViewActivityPresenterInterface getPresenter ();
    Context getContext ();
}
