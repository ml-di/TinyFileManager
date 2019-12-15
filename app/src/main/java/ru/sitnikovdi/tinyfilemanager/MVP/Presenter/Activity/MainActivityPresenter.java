package ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity;

import android.content.Context;
import android.os.Parcelable;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.Data.RecyclerViewCategoriesMainData;
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
        getView().initCategoriesRecyclerView(R.id.main_categories_recyclerview);
        getView().initCategoriesRecyclerViewAdapter();
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
    public void categoriesAdapterClick(String type) {

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

    @Override
    public ArrayList<Parcelable> getCategoriesArrayList() {
        final ArrayList<Parcelable> categoriesList = new ArrayList<>();
        final Context context = getView().getContext();

        categoriesList.add(new RecyclerViewCategoriesMainData("DOWNLOAD", context.getString(R.string.category_download), R.drawable.ic_download_24px, R.color.iconDownloadColor));
        categoriesList.add(new RecyclerViewCategoriesMainData("IMAGE", context.getString(R.string.category_image), R.drawable.ic_image_24px, R.color.iconImageColor));
        categoriesList.add(new RecyclerViewCategoriesMainData("VIDEO", context.getString(R.string.category_video), R.drawable.ic_video_24px, R.color.iconVideoColor));
        categoriesList.add(new RecyclerViewCategoriesMainData("AUDIO", context.getString(R.string.category_audio), R.drawable.ic_audio_24px, R.color.iconAudioColor));
        categoriesList.add(new RecyclerViewCategoriesMainData("DOC", context.getString(R.string.category_document), R.drawable.ic_doc_24px, R.color.iconDocumentColor));
        categoriesList.add(new RecyclerViewCategoriesMainData("APP", context.getString(R.string.category_app), R.drawable.ic_app_24px, R.color.iconAppColor));

        return categoriesList;
    }
}
