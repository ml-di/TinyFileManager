package ru.sitnikovdi.tinyfilemanager.MVP.Presenter.Activity;

import android.os.Parcelable;
import java.io.File;
import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.sitnikovdi.tinyfilemanager.Const.SortListType;
import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesFolderData;
import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesOtherFileData;
import ru.sitnikovdi.tinyfilemanager.MVP.BasePresenter;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.Presenter.Activity.FilesViewActivityPresenterInterface;
import ru.sitnikovdi.tinyfilemanager.MVP.Interface.View.Activity.FilesViewActivityViewInterface;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.Util.FilesManager;
import ru.sitnikovdi.tinyfilemanager.Util.ListHelper;

public class FilesViewActivityPresenter extends BasePresenter<FilesViewActivityViewInterface> implements FilesViewActivityPresenterInterface {

    @Override
    public void viewIsReady() {
        getView().initFilesRecyclerView(R.id.files_view_recyclerview);
        getView().initFilesRecyclerViewAdapter();
        getView().initAppBar(R.id.files_view_appbar);
        getView().initAppBarTitle(R.id.files_view_appbar_title);
        getView().initAppBarSelectAllBtn(R.id.files_view_btn_selectAll);
        getView().initAppBarSortBtn(R.id.files_view_btn_sort);
        getView().initAppBarMenuBtn(R.id.files_view_btn_menu);
    }

    @Override
    public void fileAdapterClick(String path, String name) {
        updateList(path, name);
    }

    @Override
    public void updateList(String path, String name) {
        new Single<ArrayList<Parcelable>>() {
            @Override
            protected void subscribeActual(SingleObserver<? super ArrayList<Parcelable>> observer) {
                observer.onSuccess(ListHelper.getSortList(getFilesArrayList(path), SortListType.SORT_NAME));
            }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver<ArrayList<Parcelable>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ArrayList<Parcelable> mList) {
                getView().updateRecyclerViewAdapter(mList);
                getView().setAppBarTitleText(name);
                getView().setCurrentPath(path);
                getView().expandAppBar(true, false);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public ArrayList<Parcelable> getFilesArrayList(String path) {
        final ArrayList<Parcelable> files = new ArrayList<>();
        final ArrayList<File> filesArray =
                (path == null)
                        ? FilesManager.getFileList(getView().getContext(), getView().getTypeStorage())
                        : FilesManager.getFileList(getView().getContext(), path);

        for (File f : filesArray) {
            if (f.isDirectory()) {
                files.add(new RecyclerViewFilesFolderData(f.getPath(), f.getName(), f.listFiles().length, f.isHidden()));
            } else {
                files.add(new RecyclerViewFilesOtherFileData(f.getPath(), f.getName(), f.lastModified(), f.length(), f.isHidden()));
            }
        }
        return files;
    }
}
