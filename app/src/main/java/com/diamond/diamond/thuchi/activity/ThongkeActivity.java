package com.diamond.diamond.thuchi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.model.Chi;
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.sqldao.ChiDAO;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class ThongkeActivity extends AppCompatActivity {
    private static String TAG = "ThongkeActivity";

    private String[] xdata = {"Thu", "Chi"};
    PieChart pieChart;
    private TextView tongdthu, tongchit;
    int b, tongchi = 0, tong = 0;
    int a, tongthu = 0;
    ArrayList<Integer> integers = new ArrayList<>();
    ArrayList<Integer> integerss = new ArrayList<>();
    private ThuDAO thuDao;
    private ChiDAO chiDAO;
    private List<Chi> chis;
    float c;
    float v;
    private List<Thu> thus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tongdthu = findViewById(R.id.tongdthu);
        tongchit = findViewById(R.id.tongdchi);
        pieChart = findViewById(R.id.piechart);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Thu Chi");
        pieChart.setCenterTextSize(10);
//    pieChart.setDrawEntryLabels(true);
        tongthu();
        addDataSet();

        tongdthu.setText(String.valueOf(tongthu));
        tongchit.setText(String.valueOf(tongchi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addDataSet() {
        float[] ydata = {c, v};
        ArrayList<PieEntry> yEntry = new ArrayList<>();
        ArrayList<String> xEntry = new ArrayList<>();

        for (int i = 0; i < ydata.length; i++) {
            yEntry.add(new PieEntry(ydata[i], i));
        }
        for (int i = 0; i < xdata.length; i++) {
            xEntry.add(xdata[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntry, "");
        pieDataSet.setSliceSpace(2);

        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);


        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    private void tongthu() {
        Thu thu;
        thuDao = new ThuDAO(ThongkeActivity.this);
        thus = thuDao.getALLThu();
        for (int i = 0; i < thus.size(); i++) {
            thu = thus.get(i);
            a = Integer.parseInt(thu.date);
            integers.add(a);
        }

        Chi chi;
        chiDAO = new ChiDAO(ThongkeActivity.this);
        chis = chiDAO.getALLChi();
        for (int j = 0; j < chis.size(); j++) {
            chi = chis.get(j);
            b = Integer.parseInt(chi.sotien);
            integerss.add(b);
        }
        for (Integer element : integerss) {
            tongchi += element;

        }
        for (Integer element : integers) {
            tongthu += element;

        }
        tong = tongthu + tongchi;

        c = (float) tongthu / tong * 100;
        v = (float) tongchi / tong * 100;
    }

}
