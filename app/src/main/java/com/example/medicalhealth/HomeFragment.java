package com.example.medicalhealth;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.medicalhealth.MainActivity.REQUEST_ALBUM_CODE;
import static com.example.medicalhealth.MainActivity.REQUEST_CAMERA_CODE;
import static com.example.medicalhealth.MainActivity.SELECT_LOCAL_PICTURE;
import static com.example.medicalhealth.MainActivity.TAKE_PICTURE;

public class HomeFragment extends Fragment {

    private TextView text_name, text_condition;
    private String name;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUserInfo();
        uploadPicture();
        viewLog();
    }

    private void initUserInfo() {
        Intent intent = activity.getIntent();
        name = intent.getStringExtra("Username");
        text_name = activity.findViewById(R.id.text_name);
        text_name.setText(name);
        text_condition = activity.findViewById(R.id.text_condition);
        text_condition.setText("在线");
    }

    private void uploadPicture() {
        Button uploadBtn = activity.findViewById(R.id.upload_btn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setItems(R.array.upload_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            takePicture();
                        } else if (which == 1) {
                            selectLocalPicture();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void takePicture() {
        int permissionCamera = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_CODE);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivityForResult(intent, TAKE_PICTURE);
            }
        }
    }

    private void selectLocalPicture() {
        int permissionAlbum = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionAlbum != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_ALBUM_CODE);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            activity.startActivityForResult(intent, SELECT_LOCAL_PICTURE);
        }
    }

    private void viewLog() {
        Button logBtn = activity.findViewById(R.id.log_btn);
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "点击了健康日志", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
