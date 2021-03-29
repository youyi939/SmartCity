package com.example.smartcity_test2.ui.home.adapter.recyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smartcity_test2.R;
import com.example.smartcity_test2.ui.home.pojo.ItemNew;

import java.util.List;

public class RecyclerAdapter_Label extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemNew> itemNewList;
    private RecyclerView.ViewHolder holder;
    private ItemNew item;
    private Handler handler;

    public RecyclerAdapter_Label(List<ItemNew> itemNews,Handler handler) {
        this.itemNewList = itemNews;
        this.handler  =handler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_label,parent,false);
        holder = new RecyclerView.ViewHolder(view) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        item=itemNewList.get(position);
        TextView label = holder.itemView.findViewById(R.id.item_label);
        label.setText(item.getLabel());
        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.what=3;
                message.obj=position;
                handler.sendMessage(message);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemNewList.size();
    }
}
