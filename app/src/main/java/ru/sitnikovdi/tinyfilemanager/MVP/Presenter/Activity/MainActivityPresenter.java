package ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity;

import ru.sitnikovdi.tinyfilemanager.MVP.BasePresenter;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.MainActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.MainActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.R;

public class MainActivityPresenter extends BasePresenter<MainActivityViewInterface> implements MainActivityPresenterInterface {

    @Override
    public void viewIsReady() {
        getView().initSettingsBtn(R.id.main_btn_settings);
    }

    @Override
    public void settingsBtnClick() {

    }

    @Override
    public void storageAdapterClick() {

    }

    @Override
    public void categoriesAdapterClick() {

    }
}
