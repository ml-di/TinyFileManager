package ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity;

import ru.sitnikovdi.tinyfilemanager.MVP.BasePresenter;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.PermissionActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.PermissionActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.R;

public class PermissionActivityPresenter extends BasePresenter<PermissionActivityViewInterface> implements PermissionActivityPresenterInterface {

    @Override
    public void viewIsReady() {
        getView().initBtnNext(R.id.btn_permission_next);
    }

    @Override
    public void onClickButtonNext() {
        getView().getPermission();
    }
}
