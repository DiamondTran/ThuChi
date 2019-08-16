package com.diamond.diamond.thuchi.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.model.Chi;
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ChiDAO;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddKCActivity extends AppCompatActivity {
    private TextView edtdate;
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
    String tenvi;
    int a, tongthu = 0;
    private ThuDAO thuDao;
    private List<Thu> thus;
    ArrayList<Integer> integers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        tongkthu();
        Spinner();
        chonngay();
        add();





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void chonngay() {
        Thread t = new Thread(){
        @Override
            public  void run(){
            try{
                while (!isInterrupted()){
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        long date = System.currentTimeMillis();
                        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyy\nhh mm ss a");
                         String  dateString= sdf.format(date);
                         edtdate.setText(dateString);
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        };
        t.start();

    }

    private void init() {
        edtdate = findViewById(R.id.edtdatet);
        edtmakc = findViewById(R.id.edtmakc);
        edttenkc = findViewById(R.id.edtnamekc);
        edtnote = findViewById(R.id.edtnote);
        edttienc = findViewById(R.id.edtsotienc);
        btnadd = findViewById(R.id.btnadd);
        btncancal = findViewById(R.id.btncacal);
        spinner = findViewById(R.id.spiner);

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

    private void Spinner() {
        Vi vi;
        namevis = new ArrayList<>();
        viDao = new ViDao(AddKCActivity.this);
        vis = viDao.getALLVi();
        for (int i = 0; i < vis.size(); i++) {
            vi = vis.get(i);
            namevis.add(vi.tenvi);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, namevis);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenvi = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void add() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Chi chi = new Chi();
                ChiDAO chiDAO = new ChiDAO(AddKCActivity.this);
                chi.namechi = edttenkc.getText().toString();
                chi.sotien = edttienc.getText().toString();
                chi.date = edtdate.getText().toString();
                chi.machi = edtmakc.getText().toString();
                chi.note = edtnote.getText().toString();


                if (chi.machi.matches("")) {
                    edtmakc.setError("Không được nhập trống"); }
                else if (chi.sotien.matches("")){
                    edttienc.setError("Không được nhập trống");
                }else if (Integer.parseInt(chi.sotien) <= 0){
                    edttienc.setError("Giá phải lớn hơn không");
                } else if (Integer.parseInt(chi.sotien ) > tongthu){
                    Toast.makeText(AddKCActivity.this, "Số sư trong ví không đủ", Toast.LENGTH_SHORT).show();
                }
                else if (chi.namechi.matches("")) {
                    edttenkc.setError("Không được nhập trống");
                } else if (edttenkc.equals("")) {
                    edttienc.setError("Không được nhập trống");
                } else if (chi.date.matches("")) {
                    edtdate.setError("Không được nhập trống");
                }  else if (chi.note.matches("")) {
                    edtnote.setError("Không được nhập trống");
                } else {
                    chiDAO.insertChi(chi);
                    Toast.makeText(AddKCActivity.this, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(AddKCActivity.this, ChiActivity.class);
                    intent.putExtra("tenvi",tenvi);
                    AddKCActivity.this.startActivity(intent);
                }
            }
        });
    }
    private void tongkthu(){
        Thu thu;
        thuDao = new ThuDAO(AddKCActivity.this);
        thus = thuDao.getALLThu();
        for (int i = 0; i < thus.size(); i++) {
            thu = thus.get(i);
            a = Integer.parseInt(thu.date);
            integers.add(a);
        }
        for (Integer element : integers) {
            tongthu += element;

        }

    }

}
