package com.diamond.diamond.thuchi.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.diamond.diamond.thuchi.R;

public class InforthuActivity extends AppCompatActivity {
private TextView tvmathu,tvnamethu,tvsotien,tvngay,tvghichu,tvvi;
String ma,name,date,note,tien,vi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforthu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvmathu= findViewById(R.id.mathu);
        tvnamethu= findViewById(R.id.namethu);
        tvsotien= findViewById(R.id.tienthu);
        tvvi = findViewById(R.id.tenvi);
        tvngay= findViewById(R.id.date);
        tvghichu= findViewById(R.id.note);

        ma=getIntent().getStringExtra("mathu");
        name= getIntent().getStringExtra("tenthu");
        date= getIntent().getStringExtra("sotien");
        note= getIntent().getStringExtra("note");
        tien= getIntent().getStringExtra("ngay");
        vi= getIntent().getStringExtra("sovi");

        tvmathu.setText(ma);
        tvnamethu.setText(name);
        tvsotien.setText(tien);
        tvvi.setText(vi);
        tvngay.setText(date);
        tvghichu.setText(note);


    }

}
