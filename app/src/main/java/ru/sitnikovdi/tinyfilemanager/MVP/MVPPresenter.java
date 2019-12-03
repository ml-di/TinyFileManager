package ru.sitnikovdi.tinyfilemanager.MVP;

public interface MVPPresenter <V extends MVPView> {

    void attachView(V mvpView);
    void detachView();
    void viewIsReady();

    V getView();

    boolean isViewAttached();

}
