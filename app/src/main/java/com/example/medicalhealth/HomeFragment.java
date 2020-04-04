package com.example.medicalhealth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private TextView text_name, text_condition;
    private String name;
    private Activity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();
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
        Intent intent = context.getIntent();
        name = intent.getStringExtra("Username");
        text_name = context.findViewById(R.id.text_name);
        text_name.setText(name);
        text_condition = context.findViewById(R.id.text_condition);
        text_condition.setText("在线");
    }

    public void uploadPicture() {
        Button uploadBtn = context.findViewById(R.id.upload_btn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setItems(R.array.upload_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = getResources().getStringArray(R.array.upload_options)[which];
                        Toast.makeText(context, "点击了"+text, Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void viewLog() {
        Button logBtn = context.findViewById(R.id.log_btn);
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了健康日志", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
