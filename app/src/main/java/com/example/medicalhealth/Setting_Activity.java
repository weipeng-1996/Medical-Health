package com.example.medicalhealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_);
        Button button_quit = findViewById(R.id.quit);
        button_quit.setOnClickListener(l);

    }
    View.OnClickListener l = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch(v.getId()){
                case R.id.quit:
                    intent.setClass(Setting_Activity.this, Login_Activity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
}
