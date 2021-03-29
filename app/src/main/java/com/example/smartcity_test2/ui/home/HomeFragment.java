package com.example.smartcity_test2.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.smartcity_test2.MainActivity;
import com.example.smartcity_test2.R;
import com.example.smartcity_test2.ui.home.adapter.listView.ListViewAdapter;
import com.example.smartcity_test2.ui.home.adapter.recyclerView.RecyclerAdapter_Label;
import com.example.smartcity_test2.ui.home.adapter.recyclerView.RecyclerAdapter_Service;
import com.example.smartcity_test2.ui.home.pojo.Item;
import com.example.smartcity_test2.ui.home.pojo.ItemNew;
import com.example.smartcity_test2.ui.home.pojo.Item_Service;
import com.example.smartcity_test2.utils.KenUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView_service;
    private List<Item_Service> itemList = new ArrayList<>();
    private RecyclerAdapter_Service recyclerAdapter_service;

    private ListView listView;
    private RecyclerView recyclerView_label;
    private List<ItemNew> itemNewList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private ListViewAdapter listViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_service = root.findViewById(R.id.recyclerView_service);
        listView = root.findViewById(R.id.list_view_item);
        recyclerView_label = root.findViewById(R.id.recycler_label);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        recyclerView_service.setLayoutManager(staggeredGridLayoutManager);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }

    /**
     * 1:标签解析完毕
     * 2:新闻对象加载解析完毕
     * 3:点击label的点击事件，刷新listview
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    recyclerAdapter_service = new RecyclerAdapter_Service(itemList, handler);
                    recyclerView_service.setAdapter(recyclerAdapter_service);
                    break;
                case 2:
                    RecyclerAdapter_Label recyclerAdapter = new RecyclerAdapter_Label(itemNewList, handler);
                    recyclerView_label.setLayoutManager(linearLayoutManager);
                    recyclerView_label.setAdapter(recyclerAdapter);
                    listViewAdapter = new ListViewAdapter(getContext(), R.layout.list_item, itemNewList.get(0).getItemList());
                    listView.setAdapter(listViewAdapter);
                    break;
                case 3:
                    int position = (int) msg.obj;
                    listViewAdapter = new ListViewAdapter(getContext(), R.layout.list_item, itemNewList.get(position).getItemList());
                    listView.setAdapter(listViewAdapter);
                    break;
            }
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Ken1", "onCreate: ");
        //请求+解析服务入口标签
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {

                try {

                    String url = "http://124.93.196.45:10002/service/service/list";
                    String json = KenUtils.getJson(url);
                    Log.i("Ken", "run: " + json);

                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String serviceName = object.getString("serviceName");
                        int id = object.getInt("id");
                        String img = "http://124.93.196.45:10002" + object.getString("imgUrl");
                        String serviceDesc = object.getString("serviceDesc");
                        Item_Service item = new Item_Service(img, serviceName, id, serviceDesc);
                        itemList.add(item);
                        Log.i("Ken", "run: " + item.toString());
                    }
                    itemList.add(new Item_Service("更多服务", "更多服务", 1, "更多服务"));

                    itemList.sort(new Comparator<Item_Service>() {
                        @Override
                        public int compare(Item_Service item1, Item_Service item2) {
                            int a = item1.getId();
                            int b = item2.getId();
                            return b - a;
                        }
                    });

                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //请求+解析新闻标签
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtils.getJson("http://124.93.196.45:10002/system/dict/data/type/press_category");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String label = object.getString("dictLabel");
                        int code = object.getInt("dictCode");
                        String url = "http://124.93.196.45:10002/press/press/list?pageNum=1&pageSize=10&pressCategory=" + code;
                        String item_json = KenUtils.getJson(url);
                        JSONObject item_jsonObject = new JSONObject(item_json);
                        JSONArray item_jsonArray = item_jsonObject.getJSONArray("rows");
                        List<Item> itemList = new ArrayList<>();
                        for (int j = 0; j < item_jsonArray.length(); j++) {
                            JSONObject item_object = item_jsonArray.getJSONObject(j);
                            String createTime = item_object.getString("createTime");
                            String title = item_object.getString("title");
                            String content = item_object.getString("content");
                            String imgUrl = "http://124.93.196.45:10002" + item_object.getString("imgUrl");
                            int viewsNumber = item_object.getInt("viewsNumber");
                            itemList.add(new Item(createTime, imgUrl, title, content, viewsNumber));
                        }
                        itemNewList.add(new ItemNew(label, code, itemList));
                    }
                    Log.i("Ken1", "run: " + itemNewList.toString());
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Ken1", "onStart: ");
        if (itemNewList.size() != 0) {
            Log.i("Ken1", "onStart: 不为空");
            Message message = new Message();
            message.what = 2;
            handler.sendMessage(message);
        } else {
            Log.i("Ken1", "onStart: 为空");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Ken1", "onResume: ");
        if (itemNewList.size() != 0) {
            Log.i("Ken1", "onStart: 不为空");
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        } else {
            Log.i("Ken1", "onStart: 为空");
        }
    }
}