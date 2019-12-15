package ru.sitnikovdi.tinyfilemanager.RecyclerViewAdapter;

import android.content.Context;
import android.os.Parcelable;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.Activity.MainActivityView;
import ru.sitnikovdi.tinyfilemanager.Data.RecyclerViewImageData;
import ru.sitnikovdi.tinyfilemanager.Data.RecyclerViewStorageMainData;
import ru.sitnikovdi.tinyfilemanager.R;
import ru.sitnikovdi.tinyfilemanager.Util.ProgressBarAnimation;

public class RecyclerViewStorageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_MAIN = 0;
    private static final int ITEM_BUTTON = 1;

    private ArrayList<Parcelable> mList;
    private Context mContext;

    static class MainItemViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout item;
        private AppCompatTextView title;
        private AppCompatTextView subTitle;
        private AppCompatImageView icon;
        private ProgressBar progressBar;

        MainItemViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.rv_storage_item);
            title = itemView.findViewById(R.id.rv_storage_item_title);
            subTitle = itemView.findViewById(R.id.rv_storage_item_subtitle);
            icon = itemView.findViewById(R.id.rv_storage_item_icon);
            progressBar = itemView.findViewById(R.id.rv_storage_item_progressbar);
        }

        void setMainContent(RecyclerViewStorageMainData mainData, Context context) {
            icon.setImageResource(mainData.getIconRes());

            final String memory = Formatter.formatFileSize(context, mainData.getMemory());
            final String busyMemory = Formatter.formatFileSize(context, mainData.getMemory() - mainData.getFreeMemory());
            final String titleText = (mainData.getType() == 0)
                    ? String.format(context.getString(R.string.internal_storage), memory)
                    : String.format(context.getString(R.string.external_storage), memory);

            final int progress = (int) ((mainData.getMemory() - mainData.getFreeMemory()) / (mainData.getMemory() / 100));
            final ProgressBarAnimation progressBarAnimation = new ProgressBarAnimation(progressBar, 0, progress);
            progressBarAnimation.setDuration(1000);
            progressBarAnimation.setInterpolator(new DecelerateInterpolator());
            progressBar.setAnimation(progressBarAnimation);

            title.setText(titleText);
            subTitle.setText(String.format(context.getString(R.string.busy), busyMemory));
            item.setOnClickListener(v -> ((MainActivityView) context).getPresenter().storageAdapterClick(mainData.getType()));
        }
    }

    static class ButtonItemViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout button;
        private AppCompatImageView icon;

        ButtonItemViewHolder(@NonNull View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.rv_storage_btn);
            icon = itemView.findViewById(R.id.rv_storage_btn_icon);
        }

        void setButton(RecyclerViewImageData imageData, Context context) {
            icon.setImageResource(imageData.getImageRes());
            button.setOnClickListener(v -> ((MainActivityView) context).getPresenter().storageAdapterButtonClick());
        }
    }

    public RecyclerViewStorageAdapter(ArrayList<Parcelable> mList) {
        this.mList = mList;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.mContext = recyclerView.getContext();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final RecyclerView.ViewHolder mViewHolder;

        switch (viewType) {

            case ITEM_MAIN:
                final View viewMain = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_main_storage_main_item, parent, false);
                mViewHolder = new MainItemViewHolder(viewMain);
                break;

            case ITEM_BUTTON:
                final View viewAddButton = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_main_storage_button_item, parent, false);
                mViewHolder = new ButtonItemViewHolder(viewAddButton);
                break;

            default:
                mViewHolder = null;
                break;
        }

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = holder.getItemViewType();
        switch (viewType) {
            case ITEM_MAIN:
                final RecyclerViewStorageMainData mainData = (RecyclerViewStorageMainData) mList.get(position);
                ((MainItemViewHolder) holder).setMainContent(mainData, mContext);
                break;
            case ITEM_BUTTON:
                final RecyclerViewImageData imageData = (RecyclerViewImageData) mList.get(position);
                ((ButtonItemViewHolder) holder).setButton(imageData, mContext);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {

        int viewType;

        if (mList.get(position) instanceof RecyclerViewStorageMainData) {
            viewType = ITEM_MAIN;
        } else if (mList.get(position) instanceof RecyclerViewImageData) {
            viewType = ITEM_BUTTON;
        } else {
            viewType = -1;
        }

        return viewType;
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).hashCode();
    }
}
