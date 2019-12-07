package ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity;

import android.content.Context;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.MainActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPView;

public interface MainActivityViewInterface extends MVPView {

    void initSettingsBtn (int resId);
    void initStorageRecyclerView (int resId);
    void initCategoriesRecyclerView (int resId);

    MainActivityPresenterInterface getPresenter ();
    Context getContext ();

}
