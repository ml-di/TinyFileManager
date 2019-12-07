package ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity;

import android.content.Context;
import android.os.Parcelable;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.MainActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPPresenter;

public interface MainActivityPresenterInterface extends MVPPresenter<MainActivityViewInterface> {

    void settingsBtnClick();
    void storageAdapterClick();
    void storageAdapterButtonClick();
    void categoriesAdapterClick();

    ArrayList<Parcelable> getStorageArrayList();
}
