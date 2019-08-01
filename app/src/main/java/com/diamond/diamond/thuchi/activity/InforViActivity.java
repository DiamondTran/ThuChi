package com.diamond.diamond.thuchi.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.adapter.AdapterVi;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.util.ArrayList;
import java.util.List;

public class InforViActivity extends AppCompatActivity {
    private TextView tvma, tvname, tvtongthu, tvtongchi;
    String ma, name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_vi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void init() {
        tvma = findViewById(R.id.mavi);
        tvname = findViewById(R.id.namevi);
        tvtongthu = findViewById(R.id.tongthu);
        tvtongchi = findViewById(R.id.tongchi);

        ma = getIntent().getStringExtra("ma");
        name = getIntent().getStringExtra("name");
        tvma.setText(ma);
        tvname.setText(name);




    }

}
