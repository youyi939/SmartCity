package com.example.smartcity_test2.ui.home.adapter.listView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.smartcity_test2.R;
import com.example.smartcity_test2.ui.home.pojo.Item;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Item> {
    private int resourceId;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item item =  getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.news_title);
        TextView content = (TextView) convertView.findViewById(R.id.news_content);
        ImageView image = (ImageView) convertView.findViewById(R.id.news_image);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView viewNum = (TextView) convertView.findViewById(R.id.viewsNum);

        title.setText(item.getTitle());
        content.setText(item.getContent());

        Glide.with(convertView).load(item.getImgUrl()).into(image);

        date.setText(item.getCreateTime());
        viewNum.setText(String.valueOf(item.getViewsNumber()));

        return convertView;
    }
}
