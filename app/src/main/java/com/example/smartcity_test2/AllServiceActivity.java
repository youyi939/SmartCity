package com.example.smartcity_test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.smartcity_test2.ui.home.adapter.recyclerView.RecyclerAdapter_Service_All;
import com.example.smartcity_test2.ui.home.pojo.Item_Service;
import com.example.smartcity_test2.utils.KenUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class AllServiceActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<Item_Service> itemList = new ArrayList<>();
    private RecyclerAdapter_Service_All adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_service);
        recyclerView = findViewById(R.id.recycler_view_all);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    String url = "http://124.93.196.45:10002/service/service/list";
                    String json = KenUtils.getJson(url);
                    Log.i("Ken", "run: "+json);

                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String serviceName = object.getString("serviceName");
                        int id = object.getInt("id");
                        String img = "http://124.93.196.45:10002"+object.getString("imgUrl");
                        String serviceDesc = object.getString("serviceDesc");
                        Item_Service item = new Item_Service(img,serviceName,id,serviceDesc);
                        itemList.add(item);
                        Log.i("Ken", "run: "+item.toString());
                    }

                    //根据ID给list排序
                    itemList.sort(new Comparator<Item_Service>() {
                        @Override
                        public int compare(Item_Service item1, Item_Service item2) {
                            int a = item1.getId();
                            int b = item2.getId();
                            return b-a;
                        }
                    });

                    Message message = new Message();
                    message.what=1;
                    handler.sendMessage(message);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    adapter1 = new RecyclerAdapter_Service_All(itemList);
                    recyclerView.setAdapter(adapter1);
                    break;
            }
        }
    };
}