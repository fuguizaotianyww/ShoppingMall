package com.example.shoppingmall.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppingmall.R;
import com.example.shoppingmall.alipay.AlipayOfSandbox;
import com.example.shoppingmall.entity.Order;

import java.util.List;

public class OrderAdapter extends BaseAdapter {

    List<Order> orderlist;
    private Context context;


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    //点击事件接口回调
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    public OrderAdapter(Context context, List<Order> list) {
        this.context = context;
        this.orderlist = list;
    }

    @Override
    public int getCount() {
        return orderlist != null?orderlist.size():0;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(context,R.layout.order_list,null);
            holder = new ViewHolder();
            holder.orderid = convertView.findViewById(R.id.orderId);
            holder.orderprice = convertView.findViewById(R.id.orderprice);
            holder.person = convertView.findViewById(R.id.person);
            holder.address = convertView.findViewById(R.id.address);
            holder.phone = convertView.findViewById(R.id.phone);
            holder.orderstatus = convertView.findViewById(R.id.orderstatus);
            holder.pay = convertView.findViewById(R.id.pay);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.orderid.setText(orderlist.get(position).getOrderId());
        holder.orderprice.setText(orderlist.get(position).getOrderMoney());
        holder.person.setText(orderlist.get(position).getReceiver());
        holder.address.setText(orderlist.get(position).getAddress());
        holder.phone.setText(orderlist.get(position).getPhone());
        holder.orderstatus.setText(orderlist.get(position).getOrderState());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(v,position);
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView orderid,orderprice,person,address,phone,orderstatus;
        Button pay;
    }
}
