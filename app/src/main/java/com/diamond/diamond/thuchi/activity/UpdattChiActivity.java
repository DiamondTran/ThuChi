package com.diamond.diamond.thuchi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.model.Chi;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ChiDAO;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.util.ArrayList;
import java.util.List;

public class UpdattChiActivity extends AppCompatActivity {
    private TextView edtdate;
    private TextView edtmakc;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatt_chi);
 init();
 spinner();
 add();

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

         edtmakc.setText(getIntent().getStringExtra("machi"));
        edttenkc.setText(getIntent().getStringExtra("tenchi"));
        edtdate.setText(getIntent().getStringExtra("ngay"));
        edttienc.setText(getIntent().getStringExtra("sotien"));
        edtnote.setText(getIntent().getStringExtra("note"));

        btncancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           startActivity(new Intent(UpdattChiActivity.this,ChiActivity.class));
           finish();
            }
        });
    }
    private void spinner() {
        Vi vi;
        namevis = new ArrayList<>();
        viDao = new ViDao(UpdattChiActivity.this);
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
                ChiDAO chiDAO = new ChiDAO(UpdattChiActivity.this);
                chi.namechi = edttenkc.getText().toString();
                chi.sotien = edttienc.getText().toString();
                chi.date = edtdate.getText().toString();
                chi.machi = edtmakc.getText().toString();
                chi.note = edtnote.getText().toString();


                if (chi.machi.matches("")) {
                    edtmakc.setError("Không được nhập trống");
                } else if (chi.namechi.matches("")) {
                    edttenkc.setError("Không được nhập trống");
                } else if (edttenkc.equals("")) {
                    edttienc.setError("Không được nhập trống");
                } else if (chi.date.matches("")) {
                    edtdate.setError("Không được nhập trống");
                }  else if (chi.note.matches("")) {
                    edtnote.setError("Không được nhập trống");
                } else {
                    chiDAO.updateChi(chi);
                    Toast.makeText(UpdattChiActivity.this, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(UpdattChiActivity.this, ChiActivity.class);
                    startActivity(intent);


                }
            }
        });
    }
}
