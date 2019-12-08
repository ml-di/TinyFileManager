package ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity;

import android.os.Parcelable;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.Data.RecyclerViewImageData;
import ru.sitnikovdi.tinyfilemanager.Data.RecyclerViewStorageMainData;
import ru.sitnikovdi.tinyfilemanager.MVP.BasePresenter;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.MainActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.MainActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.Util.GetStorageMemory;

public class MainActivityPresenter extends BasePresenter<MainActivityViewInterface> implements MainActivityPresenterInterface {

    @Override
    public void viewIsReady() {
        getView().initSettingsBtn(R.id.main_btn_settings);
        getView().initStorageRecyclerView(R.id.main_storage_recyclerview);
        getView().initStorageRecyclerViewAdapter();
    }

    @Override
    public void settingsBtnClick() {

    }

    @Override
    public void storageAdapterClick(int type) {

    }

    @Override
    public void storageAdapterButtonClick() {

    }

    @Override
    public void categoriesAdapterClick() {

    }

    @Override
    public ArrayList<Parcelable> getStorageArrayList() {
        final ArrayList<Parcelable> storageList = new ArrayList<>();

        // Внутренняя память
        final long internalFreeMemory = GetStorageMemory.getFreeSpace(0, getView().getContext());
        final long internalTotalMemory = GetStorageMemory.getTotalSpace(0, getView().getContext());

        storageList.add(new RecyclerViewStorageMainData(0, internalTotalMemory, internalFreeMemory, R.drawable.ic_phone_24px));

        // MicroSD карта
        if (GetStorageMemory.isSDCardAvailable(getView().getContext())) {
            storageList.add(new RecyclerViewStorageMainData(
                    1,
                    GetStorageMemory.getTotalSpace(1, getView().getContext()),
                    GetStorageMemory.getFreeSpace(1, getView().getContext()),
                    R.drawable.ic_sd_storage_24px)
            );
        }

        storageList.add(new RecyclerViewImageData(R.drawable.ic_add_24px));

        return storageList;
    }
}
