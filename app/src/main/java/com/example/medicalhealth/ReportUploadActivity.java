package com.example.medicalhealth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import static com.example.medicalhealth.MainActivity.SELECT_LOCAL_PICTURE;
import static com.example.medicalhealth.MainActivity.TAKE_PICTURE;

public class ReportUploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_upload);
        init();
        initImage();
    }

    private void init() {
        final String[] reportTypes = {"类型A", "类型B", "类型C"};
        Spinner spinner = findViewById(R.id.spinner_report_types);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, reportTypes);
        spinner.setAdapter(spinnerAdapter);
    }

    private void initImage() {
        Intent intent = getIntent();
        ImageView imageView = findViewById(R.id.picture_to_be_uploaded);
        int type = intent.getIntExtra("type", 0);
        if (type == TAKE_PICTURE) {
            Bitmap bitmap = intent.getParcelableExtra("takePicture");
            imageView.setImageBitmap(bitmap);
        }
        else if (type == SELECT_LOCAL_PICTURE) {
            Uri imageUri = intent.getData();
            imageView.setImageURI(imageUri);
        }
    }
}
