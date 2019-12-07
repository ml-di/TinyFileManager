package ru.sitnikovdi.tinyfilemanager.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.MainActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.MainActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity.MainActivityPresenter;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.RecyclerViewAdapter.RecyclerViewStorageAdapter;
import ru.sitnikovdi.tinyfilemanager.Util.LightStatusBar;

public class MainActivityView extends AppCompatActivity implements MainActivityViewInterface {

    private MainActivityPresenterInterface presenter;

    private ConstraintLayout settingsBtn;
    private RecyclerView storageRecyclerView;
    private RecyclerView.Adapter storageRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LightStatusBar.setLight(true, true, this, false);
        init();
    }

    private void init() {
        presenter = new MainActivityPresenter();
        presenter.attachView(this);
        if (presenter.isViewAttached()) {
            presenter.viewIsReady();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter.isViewAttached() && isFinishing()) {
            presenter.detachView();
        }
    }

    @Override
    public void initSettingsBtn(int resId) {
        settingsBtn = findViewById(resId);
        settingsBtn.setOnClickListener(v -> presenter.settingsBtnClick());
    }

    @Override
    public void initStorageRecyclerView(int resId) {
        storageRecyclerView = findViewById(resId);
        storageRecyclerView.setHasFixedSize(true);
        storageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initCategoriesRecyclerView(int resId) {

    }

    @Override
    public void initStorageRecyclerViewAdapter() {
        storageRecyclerViewAdapter = new RecyclerViewStorageAdapter(presenter.getStorageArrayList());
        storageRecyclerView.setAdapter(storageRecyclerViewAdapter);
    }

    @Override
    public void initCategoriesRecyclerViewAdapter() {

    }

    @Override
    public MainActivityPresenterInterface getPresenter() {
        return presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
