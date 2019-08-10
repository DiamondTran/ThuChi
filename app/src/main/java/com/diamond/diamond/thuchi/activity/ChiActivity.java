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
import com.diamond.diamond.thuchi.adapter.AdapterChi;
import com.diamond.diamond.thuchi.model.Chi;
import com.diamond.diamond.thuchi.sqldao.ChiDAO;

import java.util.List;

public class ChiActivity extends AppCompatActivity {
    private RecyclerView recyc;
    private AdapterChi adapterChi;
    private LinearLayoutManager linearLayoutManager;
    private List<Chi> chiList;
    private ChiDAO chiDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(ChiActivity.this,AddKCActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chiDao= new ChiDAO(ChiActivity.this);
        recyc= findViewById(R.id.recyclerview);
        chiList= chiDao.getALLChi();
        adapterChi= new AdapterChi(this,chiList);
        linearLayoutManager= new LinearLayoutManager(this);
        recyc.setLayoutManager(linearLayoutManager);
        recyc.setAdapter(adapterChi);
        adapterChi.notifyDataSetChanged();
    }

}
