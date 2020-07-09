package com.example.shoppingmall.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoppingmall.Adapter.SearchByAdapter;
import com.example.shoppingmall.HttpUtil;
import com.example.shoppingmall.R;
import com.example.shoppingmall.entity.Cart;
import com.example.shoppingmall.entity.Goods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class TypeFragment extends Fragment {
    private static final int GRT_GOOD = 1;
    private GridView gridView;
    private String[] titArray;
    private ListView listView;
    private SearchByAdapter adapter;
    private EditText search;
    List<Goods> list;
    private ImageButton ic_search;
    private int[] imageArray;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg){
            switch (msg.what){
                case GRT_GOOD:
                    List<Goods> goodList = (List<Goods>) msg.obj;
                        adapter = new SearchByAdapter(goodList,getContext());
                        listView.setAdapter(adapter);
                    break;
            }
        }
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view=inflater.inflate(R.layout.type, container,false);
        gridView = view.findViewById(R.id.good_type);
        search = view.findViewById(R.id.search);
        ic_search = view.findViewById(R.id.ic_searchGood);
        gridView.setAdapter(new GoodTypeAdapter(imageArray,titArray));
        listView = view.findViewById(R.id.search_list);
        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = search.getText().toString();
                Log.d("1111111111",string);
                String url = "http://192.168.43.62:8080/competitionsystem/BlurrySearchServlet";
                inputWithOkHttp(url,string);
            }
        });
        return view;
    }

    private void inputWithOkHttp(String url, String string) {
        HttpUtil.inputWithOkHttp(url, string, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Log.i("11111",responseData);
                Gson gson = new Gson();
                list = gson.fromJson(responseData, new TypeToken<List<Goods>>() {}.getType());
                Message message = new Message();
                message.what = GRT_GOOD;
                message.obj = list;
                handler.sendMessage(message);
            }
        });
    }

    /**
     * 设置数据
     */
    private void setData() {

        imageArray = new int[9];
        imageArray[0] = R.drawable.ic_basketball;
        imageArray[1] = R.drawable.ic_run;
        imageArray[2] = R.drawable.ic_women;
        imageArray[3] = R.drawable.ic_computer;
        imageArray[4] = R.drawable.ic_phone;
        imageArray[5] = R.drawable.ic_play;
        imageArray[6] = R.drawable.ic_manclose;
        imageArray[7] = R.drawable.ic_womenclose;
        imageArray[8] = R.drawable.ic_baihuo;
        titArray = new String[]{"篮球鞋","运动鞋","女生鞋","电脑","手机","电玩","潮流男士衣服","品牌女士衣服","日用百货"};
    }
    class GoodTypeAdapter extends BaseAdapter {
        private int[]image;
        private String[]title;
        public GoodTypeAdapter(int[] imageArray, String[] titArray) {
            this.image = imageArray;
            this.title = titArray;
        }

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());//获取填充其对象
            if(convertView==null){
                convertView = inflater.inflate(R.layout.good_type_view,null);
                holder.item_image = convertView.findViewById(R.id.item_image);
                holder.item_title = convertView.findViewById(R.id.item_title);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.item_image.setImageResource(image[position]);
            holder.item_title.setText(title[position]);
            return convertView;
        }


        class ViewHolder{
            ImageView item_image;
            TextView item_title;
        }
    }
}