package ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.PermissionActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.MVPPresenter;

public interface PermissionActivityPresenterInterface extends MVPPresenter<PermissionActivityViewInterface> {

    void onClickButtonNext();
}
