package com.dlf.two.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dlf.two.DpUtils;
import com.dlf.two.R;
import com.dlf.two.bean.ImgBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RlvAdapter extends RecyclerView.Adapter<RlvAdapter.ViewHolder> {

    private List<ImgBean.ResultsBean> data = new ArrayList<>();
    private Context context;
    public boolean b = true;

    public RlvAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<ImgBean.ResultsBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ImgBean.ResultsBean resultsBean = data.get(i);
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();

        if (b) {
            float v = DpUtils.dpToPx(this.context, 400);
            layoutParams.height = (int) v;
        } else {
            int a = new Random().nextInt(800 - 300 + 1) + 300;
            float v = DpUtils.dpToPx(this.context, a);

            layoutParams.height = (int) v;
        }


        Glide.with(this.context).load(resultsBean.getUrl()).into(viewHolder.iv_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);

        }
    }
}
