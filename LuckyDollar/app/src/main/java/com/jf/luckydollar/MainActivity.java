package com.jf.luckydollar;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.jf.fragment.*;
public class MainActivity extends AppCompatActivity implements
        FantasyFragment.OnFragmentInteractionListener,
        CartFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener,
        UpcomingFragment.OnFragmentInteractionListener,
        WinnersFragment.OnFragmentInteractionListener,
        View.OnClickListener,
        RadioGroup.OnCheckedChangeListener {

    private boolean is_closed = false;
    private long mExitTime;



    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4,rb5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpMenu();


    }
    private  void setUpMenu()
    {
        rg=(RadioGroup)findViewById(R.id.rg);
        rb1=(RadioButton)findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);
        rb3=(RadioButton)findViewById(R.id.rb3);
        rb4=(RadioButton)findViewById(R.id.rb4);
        rb5=(RadioButton)findViewById(R.id.rb5);
        rg.setOnCheckedChangeListener(this);
        rb1.setChecked(true);
    }
    private void changeFragment(Fragment targetFragment) {
        //resideMenu.clearIgnoredViewList();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // 监听手机上的BACK键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 判断菜单是否关闭
            if (is_closed) {
                // 判断两次点击的时间间隔（默认设置为2秒）
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                    super.onBackPressed();
                }
            } else {
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void onCheckedChanged(RadioGroup arg0, int checkedId) {

        if (rb1.getId() == checkedId) {
            changeFragment(new FantasyFragment());
        } else if (rb2.getId() == checkedId) {
            changeFragment(new UpcomingFragment());
        } else if (rb3.getId() == checkedId) {
            changeFragment(new WinnersFragment());
        } else if (rb4.getId() == checkedId) {
            changeFragment(new CartFragment());
        } else if (rb5.getId() == checkedId) {
            changeFragment(new MeFragment());
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
