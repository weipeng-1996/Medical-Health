package com.example.medicalhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button1 = getActivity().findViewById(R.id.personal_info);
        Button button2 = getActivity().findViewById(R.id.setting);
        button1.setOnClickListener(l);
        button2.setOnClickListener(l);
    }
    View.OnClickListener l = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch(v.getId()){
                case R.id.personal_info:
                    intent.setClass(getActivity(), Personal_Info_Activity.class);
                    startActivity(intent);
                    break;

                case R.id.setting:
                    intent.setClass(getActivity(), Setting_Activity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
}
