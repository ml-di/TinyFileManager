package ru.sitnikovdi.tinyfilemanager.RecyclerViewAdapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.Activity.MainActivityView;
import ru.sitnikovdi.tinyfilemanager.Data.RecyclerViewCategoriesMainData;
import ru.sitnikovdi.tinyfilemanager.R;

public class RecyclerViewCategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int ITEM_BUTTON = 0;

    private ArrayList<Parcelable> mList;
    private Context mContext;

    static class ButtonItemViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout button;
        private AppCompatImageView icon;
        private ConstraintLayout iconBackground;
        private AppCompatTextView title;

        ButtonItemViewHolder(@NonNull View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.rv_categories_btn);
            icon = itemView.findViewById(R.id.rv_categories_btn_icon);
            iconBackground = itemView.findViewById(R.id.rv_categories_btn_icon_background);
            title = itemView.findViewById(R.id.rv_categories_btn_title);
        }

        void setButton(RecyclerViewCategoriesMainData categoriesData, Context context) {

            final ColorStateList colorList = ContextCompat.getColorStateList(context, categoriesData.getColorRes());

            icon.setImageResource(categoriesData.getIconRes());
            icon.setImageTintList(colorList);
            iconBackground.setBackgroundTintList(colorList);
            title.setText(categoriesData.getTitle());
            title.setTextColor(colorList);
            button.setOnClickListener(v -> ((MainActivityView) context).getPresenter().categoriesAdapterClick(categoriesData.getType()));
        }

    }

    public RecyclerViewCategoriesAdapter(ArrayList<Parcelable> mList) {
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

        if (viewType == ITEM_BUTTON) {
            final View viewButton = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_main_categories_item, parent, false);
            return new ButtonItemViewHolder(viewButton);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = holder.getItemViewType();
        if (viewType == ITEM_BUTTON) {
            final RecyclerViewCategoriesMainData categoriesData = (RecyclerViewCategoriesMainData) mList.get(position);
            ((ButtonItemViewHolder) holder).setButton(categoriesData, mContext);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).hashCode();
    }

    @Override
    public int getItemViewType(int position) {
        return (mList.get(position) instanceof RecyclerViewCategoriesMainData) ? ITEM_BUTTON : -1;
    }
}
