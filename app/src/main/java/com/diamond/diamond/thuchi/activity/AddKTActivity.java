package com.diamond.diamond.thuchi.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddKTActivity extends AppCompatActivity {
private EditText edtdate;
private EditText edtmakt;
private EditText edttenkt;
private EditText edttien;
private EditText edtnote;
private Button btnadd;
private Button btncancal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kt);
        Toolbar toolbar = findViewById(R.id.toolbar);
       init();
       edtdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               chonngay();
           }
       });
        setSupportActionBar(toolbar);


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
private void init(){
    edtdate= findViewById(R.id.edtdate);
    edtmakt = findViewById(R.id.edtmakt);
    edttenkt= findViewById(R.id.edtnamekt);
    edtnote = findViewById(R.id.edtnote);
    edttien= findViewById(R.id.edtsotien);
    btnadd= findViewById(R.id.btnadd);
    btncancal= findViewById(R.id.btncacal);

    btncancal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            edtdate.setText("");
            edtmakt.setText("");
            edtnote.setText("");
            edttenkt.setText("");
            edttien.setText("");
        }
    });

}
}
