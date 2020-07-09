package com.example.shoppingmall.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.entity.Goods;

import java.util.List;

public class SearchByAdapter extends BaseAdapter {
    List<Goods> goodslist;
    private Context context;
    public SearchByAdapter(List<Goods> goodslist, Context context) {
        this.goodslist = goodslist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return goodslist != null?goodslist.size():0;
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
            convertView =View.inflate(context,R.layout.good_list_search,null);
            holder = new ViewHolder();
            holder.goodsimage =convertView.findViewById(R.id.goods_image);
            holder.goodsname = convertView.findViewById(R.id.goods_name);
            holder.goodsvalue = convertView.findViewById(R.id.goods_value);
            holder.goodsins = convertView.findViewById(R.id.goods_ins);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

            final String url = "http://192.168.43.62:8080/competitionsystem/image/"+goodslist.get(position).getGoodImage();
            Glide.with(context).load(url).into(holder.goodsimage);
            holder.goodsname.setText(goodslist.get(position).getGoodName());
            holder.goodsvalue.setText(goodslist.get(position).getGoodValue());
            holder.goodsins.setText(goodslist.get(position).getGoodIntroduction());

        return convertView;
    }
    class ViewHolder{
        ImageView goodsimage;
        TextView goodsname;
        TextView goodsvalue;
        TextView goodsins;
    }
}
