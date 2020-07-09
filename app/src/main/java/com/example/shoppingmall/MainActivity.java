package com.example.shoppingmall;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoppingmall.Fragment.TypeFragment;
import com.example.shoppingmall.Fragment.CartFragment;
import com.example.shoppingmall.Fragment.HomeFragment;
import com.example.shoppingmall.Fragment.PersonFragment;

public class MainActivity extends FragmentActivity implements OnClickListener{

    private ImageView tuijian;
    private ImageView daohang;
    private ImageView faxian;
    private ImageView wode;
    FragmentManager fragmentManager;

    private HomeFragment tuiJianFragment;
    private TypeFragment daoHangFragment;
    private CartFragment faXianFragment;
    private PersonFragment woDeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tuijian=(ImageView)findViewById(R.id.tuijian);
        daohang=(ImageView)findViewById(R.id.daohang);
        faxian=(ImageView)findViewById(R.id.faxian);
        wode=(ImageView)findViewById(R.id.wode);

        fragmentManager=getSupportFragmentManager();

        tuijian.setOnClickListener(this);
        daohang.setOnClickListener(this);
        faxian.setOnClickListener(this);
        wode.setOnClickListener(this);

        defaultClick();//设置默认
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        clearClick();
        switch (v.getId()) {
            case R.id.tuijian:
                tuijian.setImageResource(R.drawable.ic_fristhome);
                selectFragment(0);
                break;
            case R.id.daohang:
                daohang.setImageResource(R.drawable.ic_type);
                selectFragment(1);
                break;
            case R.id.faxian:
                faxian.setImageResource(R.drawable.ic_car);
                selectFragment(2);
                break;
            case R.id.wode:
                wode.setImageResource(R.drawable.ic_my);
                selectFragment(3);
                break;
            default:
                break;
        }
    }
    private void selectFragment(int index) {
        // TODO Auto-generated method stub
        //开启事物
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        /*
         * 我们通过判断就不用每一次新建Fragment
         */
        switch (index) {
            case 0:
                if (tuiJianFragment==null) {
                    tuiJianFragment=new HomeFragment();
                }
                fragmentTransaction.replace(R.id.fl, tuiJianFragment);

                break;
            case 1:

                if (daoHangFragment==null) {
                    daoHangFragment=new TypeFragment();
                }
                fragmentTransaction.replace(R.id.fl, daoHangFragment);

                break;
            case 2:
			/*faXianFragment=new FaXianFragment();
			fragmentTransaction.add(R.id.fl, faXianFragment);*/
                if (faXianFragment==null) {
                    faXianFragment=new CartFragment();
                }
                fragmentTransaction.replace(R.id.fl, faXianFragment);

                break;
            case 3:
			/*woDeFragment=new WoDeFragment();
			fragmentTransaction.add(R.id.fl, woDeFragment);*/
                if (woDeFragment==null) {
                    woDeFragment=new PersonFragment();
                }
                fragmentTransaction.replace(R.id.fl, woDeFragment);

                break;

            default:
                break;
        }
        fragmentTransaction.commit();
    }
    //隐藏Fragment
    private void hiddenFragment(FragmentTransaction fragmentTransaction) {
        // TODO Auto-generated method stub
        if (tuiJianFragment!=null) {
            fragmentTransaction.hide(tuiJianFragment);
        }if (daoHangFragment!=null) {
            fragmentTransaction.hide(daoHangFragment);
        }
        if (faXianFragment!=null) {
            fragmentTransaction.hide(faXianFragment);
        }
        if (woDeFragment!=null) {
            fragmentTransaction.hide(woDeFragment);
        }

    }

    //清除上一次fragment
    private void removeFragment(FragmentTransaction fragmentTransaction) {
        // TODO Auto-generated method stub
        if (tuiJianFragment!=null) {
            fragmentTransaction.remove(tuiJianFragment);
        }if (daoHangFragment!=null) {
            fragmentTransaction.remove(daoHangFragment);
        }
        if (faXianFragment!=null) {
            fragmentTransaction.remove(faXianFragment);
        }
        if (woDeFragment!=null) {
            fragmentTransaction.remove(woDeFragment);
        }

    }

    //默认进入
    private void defaultClick() {
        // TODO Auto-generated method stub
        tuijian.setImageResource(R.drawable.ic_fristhome);
        selectFragment(0);
    }
    //清除点击事件
    private void clearClick() {
        // TODO Auto-generated method stub
        tuijian.setImageResource(R.drawable.ic_fristhome);
        daohang.setImageResource(R.drawable.ic_type);
        faxian.setImageResource(R.drawable.ic_car);
        wode.setImageResource(R.drawable.ic_my);

    }
}