package ru.sitnikovdi.tinyfilemanager.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.google.android.material.appbar.AppBarLayout;
import java.io.File;
import java.util.ArrayList;
import ru.sitnikovdi.tinyfilemanager.Const.SortListType;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.FilesViewActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.FilesViewActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity.FilesViewActivityPresenter;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.RecyclerViewAdapter.RecyclerViewFilesAdapter;
import ru.sitnikovdi.tinyfilemanager.Util.FilesManager;
import ru.sitnikovdi.tinyfilemanager.Util.LightStatusBar;
import ru.sitnikovdi.tinyfilemanager.Util.ListHelper;

public class FilesViewActivityView extends AppCompatActivity implements FilesViewActivityViewInterface {

    private FilesViewActivityPresenterInterface presenter;

    private AppBarLayout appBar;
    private RecyclerView filesRecyclerView;
    private RecyclerView.Adapter filesRecyclerViewAdapter;
    private AppCompatTextView appBarTitle;
    private ConstraintLayout appBarSelectAllBtn;
    private ConstraintLayout appBarSortBtn;
    private ConstraintLayout appBarMenuBtn;

    private int TYPE_STORAGE = -1;
    private String currentPath = null;

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
        final ArrayList<Parcelable> mList = ListHelper.getSortList(presenter.getFilesArrayList(null), SortListType.SORT_NAME);
        filesRecyclerViewAdapter = new RecyclerViewFilesAdapter(mList);
        filesRecyclerView.setAdapter(filesRecyclerViewAdapter);
    }

    @Override
    public void initAppBar(int resId) {
        appBar = findViewById(resId);
    }

    @Override
    public void initAppBarTitle(int resId) {
        appBarTitle = findViewById(resId);
    }

    @Override
    public void initAppBarSelectAllBtn(int resId) {
        appBarSelectAllBtn = findViewById(resId);
        appBarSelectAllBtn.setOnClickListener(v -> {});
    }

    @Override
    public void initAppBarSortBtn(int resId) {
        appBarSortBtn = findViewById(resId);
        appBarSortBtn.setOnClickListener(v -> {});
    }

    @Override
    public void initAppBarMenuBtn(int resId) {
        appBarMenuBtn = findViewById(resId);
        appBarMenuBtn.setOnClickListener(v -> {});
    }

    @Override
    public void expandAppBar(boolean isExpand, boolean isAnimation) {
        appBar.setExpanded(isExpand, isAnimation);
    }

    @Override
    public void updateRecyclerViewAdapter(ArrayList<Parcelable> list) {
        ((RecyclerViewFilesAdapter) filesRecyclerViewAdapter).setList(list);
        filesRecyclerViewAdapter.notifyDataSetChanged();

        if (filesRecyclerView.getLayoutManager() != null) {
            filesRecyclerView.getLayoutManager().scrollToPosition(0);
        }

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(filesRecyclerView.getContext(), R.anim.layout_recyclerview_show);
        filesRecyclerView.setLayoutAnimation(controller);
    }

    @Override
    public void setAppBarTitleText(String str) {
        if (appBarTitle != null) {
            appBarTitle.setText(str);
        }
    }

    @Override
    public void setCurrentPath(String path) {
        this.currentPath = path;
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

    @Override
    public void onBackPressed() {
        if (currentPath.equals(FilesManager.getPathStorage(this, TYPE_STORAGE))) {
            super.onBackPressed();
        } else {
            final File currentFile = new File(currentPath);
            presenter.updateList(
                    currentFile.getParentFile().getPath(),
                    currentFile.getParentFile().getName()
            );
        }
    }
}
