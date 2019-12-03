package ru.sitnikovdi.tinyfilemanager.Util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import ru.sitnikovdi.tinyfilemanager.R;

public class LightStatusBar {
    public static void setLight(boolean isLightStatusBar, boolean isLightNavigationBar, Activity mActivity, boolean isToolBar){

        /*
         *       StatusBar
         */
        final View view = mActivity.getWindow().getDecorView();
        final Window win = mActivity.getWindow();
        final WindowManager.LayoutParams winParams = win.getAttributes();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if(isLightStatusBar){
                view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }

            winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            win.setAttributes(winParams);

            if (isToolBar) {
                mActivity.getWindow().setStatusBarColor(mActivity.getColor(R.color.toolBarColor));
            } else {
                mActivity.getWindow().setStatusBarColor(mActivity.getColor(R.color.statusBarColor));
            }

        } else {
            if(isLightStatusBar)
                mActivity.getWindow().setStatusBarColor(mActivity.getResources().getColor(R.color.statusBarColor));
            else {
                /*if (SettingsController.isDarkTheme(mActivity) && isToolBar) {
                    mActivity.getWindow().setStatusBarColor(mActivity.getResources().getColor(R.color.colorToolBar));
                } else {
                    mActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                }*/
                mActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        /*
         *       NavigationBar
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(isLightNavigationBar){
                view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
            } else {
                view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
            }

            if (isToolBar) {
                view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                win.setNavigationBarColor(ContextCompat.getColor(mActivity, R.color.navigationBarColor));
            } else {
                win.setNavigationBarColor(ContextCompat.getColor(mActivity, R.color.backgroundColor));
            }

        } else if (isToolBar) {
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
}
