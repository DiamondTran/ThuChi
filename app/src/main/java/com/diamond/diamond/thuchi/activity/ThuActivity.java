package com.diamond.diamond.thuchi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.adapter.AdapterThu;
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;

import java.util.List;

public class ThuActivity extends AppCompatActivity {
    private RecyclerView recyc;
    private AdapterThu adapterThu;
    private LinearLayoutManager linearLayoutManager;
    private List<Thu> thus;
    private ThuDAO thDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(ThuActivity.this, AddKTActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        thDao= new ThuDAO(ThuActivity.this);
        recyc= findViewById(R.id.recyclerview);
        thus= thDao.getALLThu();
        adapterThu= new AdapterThu(this,thus);
        linearLayoutManager= new LinearLayoutManager(this);
        recyc.setLayoutManager(linearLayoutManager);
        recyc.setAdapter(adapterThu);
        adapterThu.notifyDataSetChanged();
    }

}
