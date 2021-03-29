package com.example.smartcity_test2.ui.home.adapter.recyclerView;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_test2.AllServiceActivity;
import com.example.smartcity_test2.R;
import com.example.smartcity_test2.ui.home.pojo.Item_Service;

import java.util.List;

public class RecyclerAdapter_Service extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.ViewHolder holder;
    private List<Item_Service> item_serviceList;
    private Handler handler;

    public RecyclerAdapter_Service(List<Item_Service> itemList, Handler handler) {
        this.item_serviceList = itemList;
        this.handler = handler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        holder = new RecyclerView.ViewHolder(view) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item_Service item_service = item_serviceList.get(position);
        TextView title = holder.itemView.findViewById(R.id.item_title_Service);
        ImageView imageView = holder.itemView.findViewById(R.id.item_img_Service);


        title.setText(item_service.getServiceName());
        if (item_service.getServiceName().equals("更多服务")){
            Glide.with(holder.itemView).load(R.drawable.ic_launcher_foreground).into(imageView);
        }else {
            Glide.with(holder.itemView).load(item_service.getImg()).into(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item_service.getServiceName().equals("更多服务")){
                    Toast.makeText(holder.itemView.getContext(),item_service.getServiceDesc(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(holder.itemView.getContext(), AllServiceActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }else {
                    Toast.makeText(holder.itemView.getContext(),item_service.getServiceDesc(),Toast.LENGTH_SHORT).show();
                    intentOther(item_service.getServiceName());

                }

            }
        });

    }


    private void intentOther(String title){
        switch (title){
            case "城市地铁":
//                Intent intent = new Intent(holder.itemView.getContext(), CitySubwayActivity.class);
//                holder.itemView.getContext().startActivity(intent);
                break;
            default:
                break;
        }
    }


    @Override
    public int getItemCount() {
        return item_serviceList.size();
    }
}












