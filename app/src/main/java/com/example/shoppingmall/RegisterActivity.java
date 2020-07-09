package com.example.shoppingmall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GET = 1;
    private static final String TAG = "LoginActivity";
    private EditText user,pwd;
    private Button resbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        resbtn = findViewById(R.id.res);
        user = findViewById(R.id.user);
        pwd = findViewById(R.id.psd);
        resbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.res:
                String resAddress="http://192.168.43.62:8080/competitionsystem/RegisterServlet";
                String resAccount = user.getText().toString();
                String resPassword = pwd.getText().toString();
                registerWithOkHttp(resAddress,resAccount,resPassword);
                break;
        }
    }

    private void registerWithOkHttp(String resAddress, String resAccount, String resPassword) {
        HttpUtil.registerWithOkHttp(resAddress, resAccount, resPassword, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseData = response.body().string();

                try {
                    final JSONObject jsonObject = new JSONObject(responseData);


                    Log.d(TAG, responseData);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (jsonObject.getBoolean("flag")==true){
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                }else{
                                    //Toast.makeText(RegisterActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
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
