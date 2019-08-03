package com.diamond.diamond.thuchi.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.util.ArrayList;
import java.util.List;

public class ChuyentienActivity extends AppCompatActivity {
private Spinner spinner1,spinner2;
    private List<Vi> vis;
    private List<String> namevis;
private ViDao viDao;
    String tenvi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyentien);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner1= findViewById(R.id.spchuyen1);
        spinner2= findViewById(R.id.spchuyen2);
        Spinner();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void Spinner(){
        Vi vi;
        namevis= new ArrayList<>();
        viDao= new ViDao(ChuyentienActivity.this);
        vis= viDao.getALLVi();
        for (int i=0;i<vis.size();i++){
            vi= vis.get(i);
            namevis.add(vi.tenvi);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,namevis);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenvi= spinner1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenvi= spinner2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}
