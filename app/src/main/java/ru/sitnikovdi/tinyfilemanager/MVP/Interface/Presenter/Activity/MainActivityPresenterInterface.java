package ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity;

import android.os.Parcelable;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.MainActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPPresenter;

public interface MainActivityPresenterInterface extends MVPPresenter<MainActivityViewInterface> {

    void settingsBtnClick();
    void storageAdapterClick(int type);
    void storageAdapterButtonClick();
    void categoriesAdapterClick(String type);

    ArrayList<Parcelable> getStorageArrayList();
    ArrayList<Parcelable> getCategoriesArrayList();
}
