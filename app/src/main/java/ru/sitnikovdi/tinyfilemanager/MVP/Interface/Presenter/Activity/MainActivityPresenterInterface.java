package ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity;

import android.content.Context;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.MainActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPPresenter;

public interface MainActivityPresenterInterface extends MVPPresenter<MainActivityViewInterface> {

    void storageAdapterClick ();
    void categoriesAdapterClick ();

}
