package ru.sitnikovdi.tinyfilemanager.RecyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

import ru.sitnikovdi.tinyfilemanager.Activity.FilesViewActivityView;
import ru.sitnikovdi.tinyfilemanager.R;

public class RecyclerViewFilesPathAdapter extends RecyclerView.Adapter<RecyclerViewFilesPathAdapter.RecyclerViewFilesPathViewHolder> {

    private List<String> mList;
    private Context mContext;

    static class RecyclerViewFilesPathViewHolder extends RecyclerView.ViewHolder {

        private final ConstraintLayout btn;
        private final AppCompatTextView title;

        RecyclerViewFilesPathViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            btn = itemView.findViewById(R.id.rv_file_path_btn);
            title = itemView.findViewById(R.id.rv_file_path_btn_title);
        }
    }

    public RecyclerViewFilesPathAdapter(final List<String> mList) {
        this.mList = mList;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mContext = recyclerView.getContext();
    }

    @NonNull
    @Override
    public RecyclerViewFilesPathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout_file_path, parent, false);
        return new RecyclerViewFilesPathViewHolder(mView, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewFilesPathViewHolder holder, int position) {
        final String name = new File(mList.get(position)).getName();
        holder.title.setText(name);
        holder.btn.setOnClickListener(v -> ((FilesViewActivityView) mContext).getPresenter().fileAdapterClick(mList.get(position), name));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<String> mList) {
        this.mList = mList;
    }
}
