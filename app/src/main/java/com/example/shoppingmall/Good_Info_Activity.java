package com.example.shoppingmall;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Good_Info_Activity extends Activity {
    private ImageView goodimage;
    private TextView goodname,goodvalue,goodintro;
    private Button addtocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good__info_);


        goodimage = findViewById(R.id.iv_goodimage);
        goodname = findViewById(R.id.good_name);
        goodvalue = findViewById(R.id.good_value);
        goodintro = findViewById(R.id.good_intro);
        addtocart = findViewById(R.id.add_to_car);
        final Intent intent = getIntent();
        String good_image = intent.getStringExtra("goodimage");
        String good_name = intent.getStringExtra("goodname");
        String good_value = intent.getStringExtra("goodvalue");
        String good_intro = intent.getStringExtra("goodintro");
//        final String goodId = intent.getStringExtra("goodId");

        final String url = "http://192.168.43.62:8080/competitionsystem/image/"+good_image;
        Glide.with(getBaseContext()).load(url).into(goodimage);
        goodname.setText(good_name);
        goodvalue.setText(good_value);
        goodintro.setText(good_intro);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = "http://192.168.43.62:8080/competitionsystem/SelectGoodsByNameServlet";
                String goodId = intent.getStringExtra("goodId");
                goodsNameWithOkhttp(address,goodId);
            }
        });
    }
    public void goodsNameWithOkhttp(String address,String goodId){
        HttpUtil.goodsNameWithOkhttp(address, goodId, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseData = response.body().string();

                try {
                    final JSONObject jsonObject = new JSONObject(responseData);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (jsonObject.getBoolean("flag")==true){
                                    Toast.makeText(Good_Info_Activity.this,"添加成功",Toast.LENGTH_SHORT).show();
                                }else{
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }
}