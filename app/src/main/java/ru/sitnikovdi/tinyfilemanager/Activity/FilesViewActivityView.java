package ru.sitnikovdi.tinyfilemanager.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.FilesViewActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.FilesViewActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity.FilesViewActivityPresenter;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.RecyclerViewAdapter.RecyclerViewFilesAdapter;
import ru.sitnikovdi.tinyfilemanager.Util.LightStatusBar;

public class FilesViewActivityView extends AppCompatActivity implements FilesViewActivityViewInterface {

    private FilesViewActivityPresenterInterface presenter;

    private RecyclerView filesRecyclerView;
    private RecyclerView.Adapter filesRecyclerViewAdapter;

    private int TYPE_STORAGE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_view_view);

        if (getIntent() != null && getIntent().getExtras() != null) {
            TYPE_STORAGE = getIntent().getExtras().getInt("TYPE");
        }

        LightStatusBar.setLight(true, true, this, false);
        init();
    }

    private void init() {
        presenter = new FilesViewActivityPresenter();
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
    public void initFilesRecyclerView(int resId) {
        filesRecyclerView = findViewById(resId);
        filesRecyclerView.setHasFixedSize(true);
        filesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initFilesRecyclerViewAdapter() {
        filesRecyclerViewAdapter = new RecyclerViewFilesAdapter(presenter.getFilesArrayList(null));
        filesRecyclerView.setAdapter(filesRecyclerViewAdapter);
    }

    @Override
    public void updateRecyclerViewAdapter(ArrayList<Parcelable> list) {
        ((RecyclerViewFilesAdapter) filesRecyclerViewAdapter).setList(list);
        filesRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public int getTypeStorage() {
        return this.TYPE_STORAGE;
    }

    @Override
    public FilesViewActivityPresenterInterface getPresenter() {
        return presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
