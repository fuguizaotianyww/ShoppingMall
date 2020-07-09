package com.example.shoppingmall.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.shoppingmall.Order_Info_List_Activity;
import com.example.shoppingmall.R;


public class PersonFragment extends Fragment {
    private TextView myorder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view=inflater.inflate(R.layout.person, container,false);
        myorder = view.findViewById(R.id.myorder);
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Order_Info_List_Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}