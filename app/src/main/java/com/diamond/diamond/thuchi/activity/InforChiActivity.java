package com.diamond.diamond.thuchi.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.diamond.diamond.thuchi.R;

public class InforChiActivity extends AppCompatActivity {
    private TextView tvmachi,tvnamechi,tvsotien,tvngay,tvghichu,tvvi;
    String ma,name,date,note,tien,vi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_chi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tvmachi= findViewById(R.id.machi);
        tvnamechi= findViewById(R.id.namechi);
        tvsotien= findViewById(R.id.tienchi);
        tvvi = findViewById(R.id.tenvi);
        tvngay= findViewById(R.id.date);
        tvghichu= findViewById(R.id.note);

        ma=getIntent().getStringExtra("machi");
        name= getIntent().getStringExtra("tenchi");
        date= getIntent().getStringExtra("ngay");
        note= getIntent().getStringExtra("note");
        tien= getIntent().getStringExtra("sotien");
        vi= getIntent().getStringExtra("sovi");

        tvmachi.setText(ma);
        tvnamechi.setText(name);
        tvsotien.setText(tien);
        tvvi.setText(vi);
        tvngay.setText(date);
        tvghichu.setText(note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
