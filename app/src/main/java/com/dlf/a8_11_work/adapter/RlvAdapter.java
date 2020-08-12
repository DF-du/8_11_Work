package com.dlf.a8_11_work.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlf.a8_11_work.R;
import com.dlf.a8_11_work.bean.NoteBean;

import java.util.ArrayList;
import java.util.List;

public class RlvAdapter extends RecyclerView.Adapter<RlvAdapter.ViewHolder> {
    private Context context;
    private List<NoteBean> noteBeans = new ArrayList<>();

    public RlvAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<NoteBean> noteBeans){
        this.noteBeans.clear();
        this.noteBeans.addAll(noteBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteBean noteBean = noteBeans.get(position);
        holder.tv_desc.setText(noteBean.getDesc());
        holder.tv_title.setText(noteBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return noteBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_desc;
        TextView tv_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
