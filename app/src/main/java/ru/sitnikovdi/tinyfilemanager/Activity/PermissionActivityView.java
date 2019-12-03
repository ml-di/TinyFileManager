package ru.sitnikovdi.tinyfilemanager.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.PermissionActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.PermissionActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity.PermissionActivityPresenter;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.Util.LightStatusBar;

public class PermissionActivityView extends AppCompatActivity implements PermissionActivityViewInterface, ActivityCompat.OnRequestPermissionsResultCallback {

    private PermissionActivityPresenterInterface presenter;
    private ConstraintLayout buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_view);
        LightStatusBar.setLight(true, true, this, false);
        init();
    }

    private void init() {
        presenter = new PermissionActivityPresenter();
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
    public void initBtnNext(int resId) {
        buttonNext = findViewById(resId);
        buttonNext.setOnClickListener(v -> presenter.onClickButtonNext());
    }

    @Override
    public void getPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        }
    }
}
