package com.diamond.diamond.thuchi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.adapter.AdapterVi;
import com.diamond.diamond.thuchi.model.Chi;
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ChiDAO;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.util.ArrayList;
import java.util.List;

public class ViActivity extends AppCompatActivity {
private RecyclerView recyc;
private AdapterVi adapterVi;
private LinearLayoutManager linearLayoutManager;
private List<Vi> vis;
private ViDao viDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(ViActivity.this, AddViActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        viDao= new ViDao(ViActivity.this);
         recyc= findViewById(R.id.recyclerview);
vis= viDao.getALLVi();
adapterVi= new AdapterVi(this,vis);
linearLayoutManager= new LinearLayoutManager(this);
recyc.setLayoutManager(linearLayoutManager);
recyc.setAdapter(adapterVi);
        adapterVi.notifyDataSetChanged();
    }

}
