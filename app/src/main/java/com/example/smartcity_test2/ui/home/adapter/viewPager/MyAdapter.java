package com.example.smartcity_test2.ui.home.adapter.viewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.smartcity_test2.R;
import com.example.smartcity_test2.ui.home.pojo.Img;

import java.util.List;

// TODO: 3/29/21 viewpager的adapter
public class MyAdapter extends PagerAdapter {
    private List<Img> imgList;
    private Context context;

    public MyAdapter(Context context, List<Img> imgs) {
        this.imgList = imgs;
        this.context = context;

    }

    //获取ViewPager显示数据个数
    @Override
    public int getCount() {
        if (imgList == null || imgList.size() <= 0) {
            return 0;
        }
        return Integer.MAX_VALUE;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //当前项离开屏幕时执行的方法
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.img_lunbo,null);
        ImageView imageView = view.findViewById(R.id.imageView);
        Glide.with(view).load(imgList.get(position % imgList.size()).getImgUrl()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(container.getContext(),"position"+position,Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view);
        return view;
    }

}
