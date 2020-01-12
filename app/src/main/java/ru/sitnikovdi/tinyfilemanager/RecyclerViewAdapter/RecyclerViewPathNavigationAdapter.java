package ru.sitnikovdi.tinyfilemanager.RecyclerViewAdapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

import ru.sitnikovdi.tinyfilemanager.Activity.FilesViewActivityView;
import ru.sitnikovdi.tinyfilemanager.Data.PathNavigation.RecyclerViewPathNavigationDividerData;
import ru.sitnikovdi.tinyfilemanager.Data.PathNavigation.RecyclerViewPathNavigationIconData;
import ru.sitnikovdi.tinyfilemanager.Data.PathNavigation.RecyclerViewPathNavigationTitleData;
import ru.sitnikovdi.tinyfilemanager.R;

public class RecyclerViewPathNavigationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int ITEM_TITLE = 0;
    private final static int ITEM_ICON = 1;
    private final static int ITEM_DIVIDER = 2;

    private List<Parcelable> mList;
    private Context mContext;

    static class TitleViewHolder extends RecyclerView.ViewHolder {

        private final ConstraintLayout btn;
        private final AppCompatTextView title;

        TitleViewHolder(@NonNull View itemView) {
            super(itemView);

            btn = itemView.findViewById(R.id.rv_path_navigation_btn);
            title = itemView.findViewById(R.id.rv_path_navigation_btn_title);
        }

        void setTitle (RecyclerViewPathNavigationTitleData titleData, Context context, boolean isLast) {
            final String name = new File(titleData.getTitle()).getName();
            title.setText(name);
            btn.setOnClickListener(v -> ((FilesViewActivityView) context).getPresenter().fileAdapterClick(titleData.getTitle(), name));

            if (isLast) {
                title.setTypeface(null, Typeface.BOLD);
            } else {
                title.setTypeface(null, Typeface.NORMAL);
            }
        }
    }

    static class IconViewHolder extends RecyclerView.ViewHolder {

        private final ConstraintLayout btn;
        private final AppCompatImageView icon;

        IconViewHolder(@NonNull View itemView) {
            super(itemView);

            btn = itemView.findViewById(R.id.rv_path_navigation_btn);
            icon = itemView.findViewById(R.id.rv_path_navigation_btn_icon);
        }

        void setIcon (RecyclerViewPathNavigationIconData iconData, Context context) {
            icon.setImageResource(iconData.getIconRes());
            btn.setOnClickListener(v -> ((FilesViewActivityView) context).getPresenter().fileAdapterClick(iconData.getPath(), new File(iconData.getPath()).getName()));
        }
    }

    static class DividerViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatImageView divider;

        DividerViewHolder(@NonNull View itemView) {
            super(itemView);

            divider = itemView.findViewById(R.id.rv_path_navigation_btn_next_icon);
        }

        void setDivider (RecyclerViewPathNavigationDividerData dividerData) {
            divider.setImageResource(dividerData.getDividerIconRes());
        }
    }

    public RecyclerViewPathNavigationAdapter(final List<Parcelable> mList) {
        this.mList = mList;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mContext = recyclerView.getContext();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View view;
        if (viewType == ITEM_TITLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_path_navigation_title_item, parent, false);
            return new TitleViewHolder(view);
        } else if (viewType == ITEM_ICON) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_path_navigation_icon_item, parent, false);
            return new IconViewHolder(view);
        } else if (viewType == ITEM_DIVIDER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_path_navigation_divider_item, parent, false);
            return new DividerViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case ITEM_TITLE:
                final boolean isLast = (position + 1 == getItemCount());
                final RecyclerViewPathNavigationTitleData titleData = (RecyclerViewPathNavigationTitleData) mList.get(position);
                ((TitleViewHolder) holder).setTitle(titleData, mContext, isLast);
                break;
            case ITEM_ICON:
                final  RecyclerViewPathNavigationIconData iconData = (RecyclerViewPathNavigationIconData) mList.get(position);
                ((IconViewHolder) holder).setIcon(iconData, mContext);
                break;
            case ITEM_DIVIDER:
                final RecyclerViewPathNavigationDividerData dividerData = (RecyclerViewPathNavigationDividerData) mList.get(position);
                ((DividerViewHolder) holder).setDivider(dividerData);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof RecyclerViewPathNavigationTitleData) {
            return ITEM_TITLE;
        } else if (mList.get(position) instanceof RecyclerViewPathNavigationIconData) {
            return ITEM_ICON;
        } else if (mList.get(position) instanceof RecyclerViewPathNavigationDividerData) {
            return ITEM_DIVIDER;
        } else {
            return -1;
        }
    }

    public void setList(List<Parcelable> mList) {
        this.mList = mList;
    }
}
