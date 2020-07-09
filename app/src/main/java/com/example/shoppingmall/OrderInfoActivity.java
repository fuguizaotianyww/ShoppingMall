package com.example.shoppingmall;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shoppingmall.alipay.AlipayOfSandbox;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OrderInfoActivity extends Activity {
    public static final int RESULT_CODE_ALIPAY = 1;
    private TextView orderId,prices;
    private EditText recevicer,address,phone;
    private Button insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);

        orderId = findViewById(R.id.order_id);
        prices = findViewById(R.id.price);
        recevicer = findViewById(R.id.recevice);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        insert = findViewById(R.id.order_insert);

        final Intent intent = getIntent();
        String price = intent.getStringExtra("price");

        Random random = new Random();
        String result="";
        for(int i=0;i<8;i++){
            result += (random.nextInt(9)+1);
        }
        Log.d("111111",result);
        orderId.setText(result);
        prices.setText(price);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.43.62:8080/competitionsystem/SaveOrderServlet";
                String orderid = orderId.getText().toString();
                String rece = recevicer.getText().toString();
                String ads = address.getText().toString();
                String phonenum = phone.getText().toString();
                String price = prices.getText().toString();
                saveOrderWithOkhttp(url,orderid,rece,ads,phonenum,price);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void saveOrderWithOkhttp(String url, String orderid, String rece, String ads, String phonenum, String price) {
        HttpUtil.saveOrderWithOkhttp(url, orderid, rece, ads, phonenum, price, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }
}