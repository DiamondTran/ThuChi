package com.diamond.diamond.thuchi.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddKCActivity extends AppCompatActivity {
    private EditText edtdate;
    private EditText edtmakc;
    private EditText edttenkc;
    private EditText edttienc;
    private Spinner spinner;
    private EditText edtnote;
    private Button btnadd;
    private ViDao viDao;
    private List<String> namevis;
    private List<Vi> vis;
    private Button btncancal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
init();
Spinner();
        edtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonngay();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void chonngay(){
        final Calendar cal=Calendar.getInstance();
        int year= cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day= cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/mm/yyy");
                edtdate.setText(simpleDateFormat.format(cal.getTime()));
            }
        }, year, month, day);
        date.show();
    }

    private void init() {
        edtdate= findViewById(R.id.edtdate);
        edtmakc = findViewById(R.id.edtmakc);
        edttenkc= findViewById(R.id.edtnamekc);
        edtnote = findViewById(R.id.edtnote);
        edttienc= findViewById(R.id.edtsotienc);
        btnadd= findViewById(R.id.btnadd);
        btncancal= findViewById(R.id.btncacal);
        spinner= findViewById(R.id.spiner);

        btncancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtdate.setText("");
                edtmakc.setText("");
                edtnote.setText("");
                edttenkc.setText("");
                edttienc.setText("");
            }
        });
    }
    private void Spinner(){
        Vi vi;
        namevis= new ArrayList<>();
        viDao= new ViDao(AddKCActivity.this);
        vis= viDao.getALLVi();
        for (int i=0;i<vis.size();i++){
            vi= vis.get(i);
            namevis.add(vi.tenvi);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,namevis);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}
