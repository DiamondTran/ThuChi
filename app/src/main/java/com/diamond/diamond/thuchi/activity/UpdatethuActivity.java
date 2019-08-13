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
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UpdatethuActivity extends AppCompatActivity {
    private TextView tvdate;
    private TextView edtmakt;
    private EditText edttenkt;
    private EditText edttien;
    private EditText edtnote;
    private Button btnadd;
    private Spinner spinner;
    private ThuDAO thuDAO;

    private Button btncancal;
    private ViDao viDao;
    private List<String> namevis;
    private List<Vi> vis;
    String tenvi,ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatethu);
        init();
        spinner();



edtmakt.setText(getIntent().getStringExtra("mathu"));
edttenkt.setText(getIntent().getStringExtra("tenthu"));
edttien.setText(getIntent().getStringExtra("ngay"));
tvdate.setText(getIntent().getStringExtra("sotien"));
edtnote.setText(getIntent().getStringExtra("note"));

    }
//


    private void add() {
        thuDAO = new ThuDAO(UpdatethuActivity.this);
        final Thu thu = new Thu();
        thu.mathu = edtmakt.getText().toString();
        thu.namethu= edttenkt.getText().toString();
        thu.sotien= edttien.getText().toString();
        thu.note = edtnote.getText().toString();
        thu.date = tvdate.getText().toString();
        thu.tenvi=tenvi;


        if (thu.mathu.matches("")) {
            edtmakt.setError("Không được nhập trống");
        } else if (thu.namethu.matches("")) {
            edttenkt.setError("Không được nhập trống");
        }  else if (thu.sotien.matches("")) {
            edttien.setError("Không được nhập trống");
        }  else if (thu.tenvi.matches("")) {
            Toast.makeText(UpdatethuActivity.this, "Phải chọn ví", Toast.LENGTH_SHORT).show();
        }  else if (thu.date.matches("")) {
            tvdate.setError("Không được nhập trống");
        }   else if (thu.note.matches("")) {
            edtnote.setError("Không được nhập trống");
        }else {
        thuDAO.updateThu(thu);
        Toast.makeText(UpdatethuActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UpdatethuActivity.this,ThuActivity.class));
    }}

    private void init() {
        tvdate= findViewById(R.id.edtdatet);
        edtmakt = findViewById(R.id.edtmakt);
        edttenkt= findViewById(R.id.edtnamekt);
        edtnote = findViewById(R.id.edtnote);
        edttien= findViewById(R.id.edtsotien);
        btnadd= findViewById(R.id.btnupdate);
        spinner= findViewById(R.id.spiner);
        btncancal= findViewById(R.id.btncacal);



        btncancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(UpdatethuActivity.this,ThuActivity.class));
               finish();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }
    private void spinner(){
        Vi vi;
        namevis= new ArrayList<>();
        viDao= new ViDao(UpdatethuActivity.this);
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
                tenvi= spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
