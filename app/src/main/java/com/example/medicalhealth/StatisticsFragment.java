package com.example.medicalhealth;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {
    private Activity activity;
    private BarChart chart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSpinner();
        initChart();
        initData();
    }

    private void initSpinner() {
        final String[] spinnerItems = {"报告1", "报告2", "报告3"};
        Spinner spinner = activity.findViewById(R.id.spinner_report_names);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        spinner.setAdapter(spinnerAdapter);
    }

    private void initChart() {
        chart = activity.findViewById(R.id.bar_chart);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setDrawBorders(false);
        chart.getXAxis().setDrawGridLines(false);
    }

    private void initData() {
        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 60f));
        entries.add(new BarEntry(2f, 50f));
        entries.add(new BarEntry(3f, 80f));
        entries.add(new BarEntry(4f, 60f));

        BarDataSet set = new BarDataSet(entries, "BarChartSet");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData data = new BarData(set);
        chart.setData(data);
        chart.invalidate();
    }
}
