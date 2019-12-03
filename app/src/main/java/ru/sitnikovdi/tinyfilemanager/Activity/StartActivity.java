package ru.sitnikovdi.tinyfilemanager.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ru.sitnikovdi.tinyfilemanager.Util.CheckPermission;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startFirstActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_CANCELED) {
                finish();
            } else if (resultCode == Activity.RESULT_OK) {
                startFirstActivity();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startFirstActivity() {
        final Intent intent;
        intent = (CheckPermission.isPermissionEnabled(this))
                ? new Intent(this, MainActivityView.class)
                : new Intent(this, PermissionActivityView.class);
        startActivityForResult(intent, 1);
    }
}
