package ru.sitnikovdi.tinyfilemanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.MainActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.MainActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.Util.LightStatusBar;

public class MainActivityView extends AppCompatActivity implements MainActivityViewInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LightStatusBar.setLight(true, true, this, false);
        init();
    }

    private void init() {

    }

    @Override
    public void initStorageRecyclerView(int resId) {

    }

    @Override
    public void initCategoriesRecyclerView(int resId) {

    }

    @Override
    public MainActivityPresenterInterface getPresenter() {
        return null;
    }

    @Override
    public Context getContext() {
        return null;
    }
}
