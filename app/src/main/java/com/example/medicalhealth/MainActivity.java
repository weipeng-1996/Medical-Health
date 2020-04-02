package com.example.medicalhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private int lastIndex;
    ArrayList<Fragment> fragments;

    private TextView text_name, text_condition;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        initUserInfo();
    }

//    protected void initUserInfo() {
//        Intent intent = getIntent();
//        name = intent.getStringExtra("Username");
//        text_name = (TextView) findViewById(R.id.text_name);
//        text_name.setText(name);
//        text_condition = (TextView) findViewById(R.id.text_condition);
//        text_condition.setText("在线");
//    }

    private void init() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new StatisticsFragment());
        fragments.add(new MineFragment());

        setFragmentPosition(0);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        setFragmentPosition(0);
                        break;
                    case R.id.menu_statistics:
                        setFragmentPosition(1);
                        break;
                    case R.id.menu_mine:
                        setFragmentPosition(2);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void setFragmentPosition(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragments.get(position);
        Fragment lastFragment = fragments.get(lastIndex);
        lastIndex = position;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.frame_layout, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }
}
