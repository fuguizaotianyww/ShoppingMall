package com.example.shoppingmall.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.HttpUtil;
import com.example.shoppingmall.OrderInfoActivity;
import com.example.shoppingmall.R;
import com.example.shoppingmall.entity.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class CartFragment extends Fragment {
    private static final int GRT_CART = 2;
    private ListView cartlistView;
    private TextView total;
    private Button settlement;
    CartListviewAdapter adapter;
    List<Cart> cartList;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GRT_CART:
                    List<Cart> allcart = (List<Cart>) msg.obj;
                    Log.e("allcart",allcart+"");
                    adapter = new CartListviewAdapter(allcart,total,settlement);
                    cartlistView.setAdapter(adapter);
                    break;
            }
        }
    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String address = "http://192.168.43.62:8080/competitionsystem/SelectAllCartServlet";
        cartWithOkHttp(address);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.cart, container, false);
        cartlistView = view.findViewById(R.id.cart_list);
        total = view.findViewById(R.id.total);
        settlement = view.findViewById(R.id.Settlement);
        return view;
    }


    private void cartWithOkHttp(String address) {
        HttpUtil.cartWithOkhttp(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
//                Log.i("11111",responseData);
                Gson gson = new Gson();
                cartList = gson.fromJson(responseData, new TypeToken<List<Cart>>() {}.getType());
                Message message = new Message();
                message.what = GRT_CART;
                message.obj = cartList;
                handler.sendMessage(message);
            }
        });

    }

    class CartListviewAdapter extends BaseAdapter {
        public static final int REGION = 1;
        private static final int GRT = 3;
        public TextView total;
        private Button sattlement;
        private List<Cart> list;
        private List<Cart> cartsList;
        private static final int Grt_A=4;
        Handler ahandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg){
                switch (msg.what){
                    case Grt_A:
                        List<Cart> alist = (List<Cart>) msg.obj;
                        adapter = new CartListviewAdapter(alist,total, settlement);
                        cartlistView.setAdapter(adapter);
                }
            }
        };

        public CartListviewAdapter(List<Cart> list, TextView textView, Button settlement) {
            this.list = list;
            this.total = textView;
            this.sattlement = settlement;
        }


        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            int totalprice = 0;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.cart_list, null);
                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.goods_image);
                holder.goodname = convertView.findViewById(R.id.goods_name);
                holder.goodvalue = convertView.findViewById(R.id.goods_value);
                holder.goodnumber = convertView.findViewById(R.id.goods_number);
                holder.add_btn = convertView.findViewById(R.id.add);
                holder.mul_btn = convertView.findViewById(R.id.multip);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final String url = "http://192.168.43.62:8080/competitionsystem/image/" + list.get(position).getGoodImage();
            Glide.with(getContext()).load(url).into(holder.imageView);
            holder.goodname.setText(list.get(position).getGoodName());
            holder.goodvalue.setText(list.get(position).getGoodValue());
            holder.goodnumber.setText(list.get(position).getGoodNumber());
            if(list != null && list.size() > 0 ){
                for(int i = 0; i < list.size(); i++){
                    totalprice = totalprice + Integer.parseInt(list.get(i).getGoodNumber())*Integer.parseInt(list.get(i).getGoodValue());
                }
            }
            holder.add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a = Integer.parseInt(holder.goodnumber.getText().toString())+1;
                    holder.goodnumber.setText(a+"");
                    int b = Integer.parseInt(list.get(position).getGoodValue());
                    holder.goodvalue.setText(a*b+"");
                    int c = Integer.parseInt(total.getText().toString());
                    String d = String.valueOf(c+(a-1)*b);
                    total.setText(d);
                }
            });
            holder.mul_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int m = Integer.parseInt(holder.goodnumber.getText().toString())-1;
                    holder.goodnumber.setText(m+"");
                    int b = Integer.parseInt(list.get(position).getGoodValue());
                    holder.goodvalue.setText(m*b+"");
                    int c = Integer.parseInt(total.getText().toString());
                    String d = String.valueOf(c+(m-2)*b);
                    total.setText(d);
                }
            });
            total.setText(String.valueOf(totalprice));
            cartlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    Log.d("123456789", "onItemClick: "+"12345678945612313248679845612345687");
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("移出购物车");
                    dialog.setMessage("是否将此商品移出购物车？");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String address = "http://192.168.43.62:8080/competitionsystem/DeleteByIdServlet";
                            String goodId = list.get(position).getGoodId();
                            deleteWithOkhttp(address,goodId);
                        }
                    });
                    dialog.setNegativeButton("再想想！", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                    return false;
                }
            });
            sattlement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), OrderInfoActivity.class);
                    String price = total.getText().toString();
                    intent.putExtra("price",price);
                    startActivityForResult(intent,REGION);
                }
            });
            return convertView;
        }


        private void deleteWithOkhttp(String address, String goodId) {
            HttpUtil.deleteWithOkhttp(address, goodId, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    final String responseData = response.body().string();
                    Gson gson = new Gson();
                    cartsList = gson.fromJson(responseData,new TypeToken<List<Cart>>() {}.getType());
                    Message message = new Message();
                    message.what = Grt_A;
                    message.obj = cartsList;
                    ahandler.sendMessage(message);
                }
            });
        }

        class ViewHolder {
            ImageView imageView;
            TextView goodname;
            TextView goodvalue;
            TextView goodnumber;
            ImageButton add_btn;
            ImageButton mul_btn;
        }
    }
}