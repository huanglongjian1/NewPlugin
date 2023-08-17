package com.android.newplugin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.newplugin.R;

import java.util.ArrayList;
import java.util.List;

public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.MyViewHolder> {
    List<String> stringList = new ArrayList<>();

    public List<String> getStringList() {
        return stringList;
    }

    public void add(String name) {
        stringList.add(name);
        notifyDataSetChanged();
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String file_name = stringList.get(position).substring(stringList.get(position).lastIndexOf("/") + 1, stringList.get(position).length());
        holder.textView.setText("点击进入:" + file_name + "插件");
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {
                    itemClick.OnItmClick(holder.getAdapterPosition(), stringList.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rv_item);
        }
    }

    public interface ItemClick {
        void OnItmClick(int position, String file_path);
    }

    private ItemClick itemClick;

    public void setOnItemClick(ItemClick click) {
        itemClick = click;
    }
}
