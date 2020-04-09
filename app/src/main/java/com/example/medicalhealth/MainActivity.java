package com.example.medicalhealth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int lastIndex;
    ArrayList<Fragment> fragments;

    static final int REQUEST_CAMERA_CODE = 1;
    static final int REQUEST_ALBUM_CODE = 2;
    static final int TAKE_PICTURE = 1;
    static final int SELECT_LOCAL_PICTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * resultCode == -1 表示拍照/选取图片成功
     * resultCode == 0 表示取消拍照/未选取图片
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = null;
        Bitmap bitmap;
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == TAKE_PICTURE) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    bitmap = (Bitmap) extras.get("data");
                    intent = new Intent(this, ReportUploadActivity.class);
                    intent.putExtra("type", TAKE_PICTURE);
                    intent.putExtra("takePicture", bitmap);
                }
            } else if (requestCode == SELECT_LOCAL_PICTURE) {
                if (data != null) {
                    Uri selectedImage = data.getData();
                    intent = new Intent(this, ReportUploadActivity.class);
                    intent.putExtra("type", SELECT_LOCAL_PICTURE);
                    intent.setData(selectedImage);
                }
            }
            startActivity(intent);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Intent intent;
        switch (requestCode) {
            case REQUEST_CAMERA_CODE:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, TAKE_PICTURE);
                }
                break;
            case REQUEST_ALBUM_CODE:
                intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, SELECT_LOCAL_PICTURE);
                break;
            default:
                break;
        }
    }

    private void init() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new StatisticsFragment());
        fragments.add(new MineFragment());

        setFragmentPosition(0);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
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
