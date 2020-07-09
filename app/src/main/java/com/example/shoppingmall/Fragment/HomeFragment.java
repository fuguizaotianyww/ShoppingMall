package com.example.shoppingmall.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.Good_Info_Activity;
import com.example.shoppingmall.HttpUtil;
import com.example.shoppingmall.R;
import com.example.shoppingmall.entity.Goods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeFragment extends Fragment{
    private static final int GRT_GOODS = 1;
    private ListView listView;
    private Banner banner;
    List<Integer> images = new ArrayList<>();
    List<Goods> list;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@Nullable Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case GRT_GOODS:
                    List<Goods> allgoodlist = (List<Goods>) msg.obj;
                    GoodListviewAdapter adapter = new GoodListviewAdapter(allgoodlist);
                    listView.setAdapter(adapter);
                    break;
            }

        }
    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String goodsAddress="http://192.168.43.62:8080/competitionsystem/AllGoodsServlet";
        goodsWithOkHttp(goodsAddress);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view=inflater.inflate(R.layout.home, container,false);
        listView = view.findViewById(R.id.good_list);
        banner = view.findViewById(R.id.banner);
        List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.p2);
        images.add(R.mipmap.p3);
        images.add(R.mipmap.p4);
        ArrayList<String> title = new ArrayList<>();
        title.add("魔偶");
        title.add("语音王");
        title.add("儿童机器人");
        banner.setImages(images);
        banner.setImageLoader(new ImageLoadBanner());
        banner.setBannerTitles(title);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setBannerAnimation(Transformer.Accordion);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.start();
        return view;
    }
    static class ImageLoadBanner extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(Integer.parseInt(path.toString()));
        }
    }


    public void goodsWithOkHttp(String goodsAddress) {
        HttpUtil.goodsWithOkhttp(goodsAddress, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseData = response.body().string();
                Gson gson = new Gson();
                list = gson.fromJson(responseData,new TypeToken<List<Goods>>() {}.getType());
                Message message = new Message();
                message.what = GRT_GOODS;
                message.obj = list;
                mHandler.sendMessage(message);
            }
        });

    }
    private class GoodListviewAdapter extends BaseAdapter {
        private static final int REGION = 1;
        private ArrayList<Goods> list;

        public GoodListviewAdapter(List<Goods> allgoodlist) {
            this.list = (ArrayList<Goods>) allgoodlist;
        }


        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
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
            final ViewHolder holder;
            if(convertView == null){
                convertView =View.inflate(getContext(), R.layout.goods_list,null);
                holder = new ViewHolder();
                holder.goodsimage =convertView.findViewById(R.id.goods_image);
                holder.goodsname = convertView.findViewById(R.id.goods_name);
                holder.goodsvalue = convertView.findViewById(R.id.goods_value);
                holder.goodsins = convertView.findViewById(R.id.goods_ins);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            final String url = "http://192.168.43.62:8080/competitionsystem/image/"+list.get(position).getGoodImage();
            Glide.with(getContext()).load(url).into(holder.goodsimage);
            holder.goodsname.setText(list.get(position).getGoodName());
            holder.goodsvalue.setText(list.get(position).getGoodValue());
            holder.goodsins.setText(list.get(position).getGoodIntroduction());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                    Intent intent = new Intent(getContext(), Good_Info_Activity.class);
                    intent.putExtra("goodimage",list.get(position).getGoodImage());
                    intent.putExtra("goodname",list.get(position).getGoodName());
                    intent.putExtra("goodvalue",list.get(position).getGoodValue());
                    intent.putExtra("goodintro",list.get(position).getGoodIntroduction());
                    intent.putExtra("goodId",list.get(position).getGoodId());
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class ViewHolder{
            ImageView goodsimage;
            TextView goodsname;
            TextView goodsvalue;
            TextView goodsins;
        }
    }

}
