package ru.sitnikovdi.tinyfilemanager.RecyclerViewAdapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesFolderData;
import ru.sitnikovdi.tinyfilemanager.R;

public class RecyclerViewFilesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int ITEM_FOLDER = 0;
    private final static int ITEM_FILE = 1;
    private final static int ITEM_IMAGE = 2;
    private final static int ITEM_VIDEO = 3;

    private ArrayList<Parcelable> mList;
    private Context mContext;

    static class FolderViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout folder;
        private AppCompatTextView title;
        private AppCompatTextView subTitle;

        FolderViewHolder(@NonNull View itemView) {
            super(itemView);

            folder = itemView.findViewById(R.id.rv_file_folder);
            title = itemView.findViewById(R.id.rv_file_folder_title);
            subTitle = itemView.findViewById(R.id.rv_file_folder_subtitle);
        }

        void setFolder(RecyclerViewFilesFolderData folderData, Context context) {

            title.setText(folderData.getTitle());
            subTitle.setText(String.format(context.getString(R.string.files), folderData.getFilesCount()));

            folder.setOnClickListener(v -> {});
        }
    }

    public RecyclerViewFilesAdapter(ArrayList<Parcelable> mList) {
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

        if (viewType == ITEM_FOLDER) {
            final View viewFolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_file_folder_item, parent, false);
            return new FolderViewHolder(viewFolder);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        if (viewType == ITEM_FOLDER) {
            final RecyclerViewFilesFolderData folderData = (RecyclerViewFilesFolderData) mList.get(position);
            ((FolderViewHolder) holder).setFolder(folderData, mContext);
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
        return (mList.get(position) instanceof RecyclerViewFilesFolderData) ? ITEM_FOLDER : -1;
    }
}
