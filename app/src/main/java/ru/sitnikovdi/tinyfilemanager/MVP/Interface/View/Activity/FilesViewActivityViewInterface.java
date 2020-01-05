package ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity;

import android.content.Context;
import android.os.Parcelable;
import java.util.ArrayList;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.FilesViewActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPView;

public interface FilesViewActivityViewInterface extends MVPView {

    void initFilesRecyclerView(int resId);
    void initFilesRecyclerViewAdapter();
    void initAppBar(int resId);
    void initAppBarTitle(int resId);
    void initAppBarSelectAllBtn(int resId);
    void initAppBarSortBtn(int resId);
    void initAppBarMenuBtn(int resId);

    void expandAppBar(boolean isExpand, boolean isAnimation);
    void updateRecyclerViewAdapter(ArrayList<Parcelable> list);
    void clearRecyclerViewAdapter();

    void setAppBarTitleText(String str);
    void setCurrentPath(String path);

    int getTypeStorage();
    FilesViewActivityPresenterInterface getPresenter ();
    Context getContext ();
}
