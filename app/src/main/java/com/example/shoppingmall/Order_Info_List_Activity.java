package com.example.shoppingmall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.shoppingmall.Adapter.OrderAdapter;
import com.example.shoppingmall.entity.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Order_Info_List_Activity extends Activity {
    private static final int GRT_ORDER = 1;
    private ListView listView;
    private List<Order> orderList;
    private Button pay;
    OrderAdapter orderAdapter;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            switch(msg.what){
                case GRT_ORDER:
                    List<Order> list = (List<Order>) msg.obj;
                    orderAdapter = new OrderAdapter(getBaseContext(),list);
                    listView.setAdapter(orderAdapter);
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__info__list_);

        listView = findViewById(R.id.order_list);
        String address = "http://192.168.43.62:8080/competitionsystem/SelectAllOrderServlet";
        AllOrderWithOkHttp(address);

    }

    private void AllOrderWithOkHttp(String address) {
        HttpUtil.allOrderWithOkhttp(address,new Callback(){

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
//                Log.i("11111",responseData);
                Gson gson = new Gson();
                orderList = gson.fromJson(responseData, new TypeToken<List<Order>>() {}.getType());
                Message message = new Message();
                message.what = GRT_ORDER;
                message.obj = orderList;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }



}