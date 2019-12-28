package ru.sitnikovdi.tinyfilemanager.RecyclerViewAdapter;

import android.content.Context;
import android.os.Parcelable;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

import ru.sitnikovdi.tinyfilemanager.Activity.FilesViewActivityView;
import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesFolderData;
import ru.sitnikovdi.tinyfilemanager.Data.Files.RecyclerViewFilesOtherFileData;
import ru.sitnikovdi.tinyfilemanager.R;

public class RecyclerViewFilesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int ITEM_FOLDER = 0;
    private final static int ITEM_OTHER_FILE = 1;
    private final static int ITEM_IMAGE = 2;
    private final static int ITEM_VIDEO = 3;
    private final static int ITEM_DOC = 4;

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

            folder.setOnClickListener(v -> ((FilesViewActivityView) context).getPresenter().fileAdapterClick(folderData.getPath()));
        }
    }

    static class OtherFileViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout file;
        private AppCompatTextView title;
        private AppCompatTextView subTitle;

        OtherFileViewHolder(@NonNull View itemView) {
            super(itemView);

            file = itemView.findViewById(R.id.rv_file_otherfile);
            title = itemView.findViewById(R.id.rv_file_otherfile_title);
            subTitle = itemView.findViewById(R.id.rv_file_otherfile_subtitle);
        }

        void setOtherFile(RecyclerViewFilesOtherFileData otherFile, Context context) {

            final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy HH:mm", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getDefault());
            final String fileEditTime = sdf.format(otherFile.getEditTime());
            final String fileSize = Formatter.formatFileSize(context, otherFile.getSize());

            title.setText(otherFile.getTitle());
            subTitle.setText(fileSize + ", " + fileEditTime);

            file.setOnClickListener(v -> {});
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
        final View view;
        if (viewType == ITEM_FOLDER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_file_folder_item, parent, false);
            return new FolderViewHolder(view);
        } else if (viewType == ITEM_OTHER_FILE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_file_otherfile_item, parent, false);
            return new OtherFileViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case ITEM_FOLDER:
                final RecyclerViewFilesFolderData folderData = (RecyclerViewFilesFolderData) mList.get(position);
                ((FolderViewHolder) holder).setFolder(folderData, mContext);
                break;
            case ITEM_OTHER_FILE:
                final RecyclerViewFilesOtherFileData otherFileData = (RecyclerViewFilesOtherFileData) mList.get(position);
                ((OtherFileViewHolder) holder).setOtherFile(otherFileData, mContext);
                break;
            case ITEM_DOC:

                break;
            case ITEM_IMAGE:

                break;
            case ITEM_VIDEO:

                break;
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
        if (mList.get(position) instanceof RecyclerViewFilesFolderData) {
            return ITEM_FOLDER;
        } else if (mList.get(position) instanceof RecyclerViewFilesOtherFileData) {
            return ITEM_OTHER_FILE;
        } else {
            return -1;
        }
    }

    public void setList(ArrayList<Parcelable> mList) {
        this.mList = mList;
    }
}
