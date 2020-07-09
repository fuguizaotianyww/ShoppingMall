package com.example.shoppingmall.alipay;

import android.os.Bundle;
import com.example.shoppingmall.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ExternalFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_alipay_of_sandbox, container, false);
	}
}
