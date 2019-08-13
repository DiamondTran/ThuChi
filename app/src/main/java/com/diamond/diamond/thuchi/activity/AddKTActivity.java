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
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddKTActivity extends AppCompatActivity {
private TextView tvdate;
private EditText edtmakt;
private EditText edttenkt;
private EditText edttien;
private EditText edtnote;
private Button btnadd;
private Spinner spinner;

private Button btncancal;
private ViDao viDao;
private List<String> namevis;
private List<Vi> vis;
String tenvi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kt);
        Toolbar toolbar = findViewById(R.id.toolbar);
       init();
       Spinner();
       add();

       chonngay();

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

private void chonngay(){
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
                            tvdate.setText(dateString);
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
private void init(){
    tvdate= findViewById(R.id.edtdatet);
    edtmakt = findViewById(R.id.edtmakt);
    edttenkt= findViewById(R.id.edtnamekt);
    edtnote = findViewById(R.id.edtnote);
    edttien= findViewById(R.id.edtsotien);
    btnadd= findViewById(R.id.btnadd);
    spinner= findViewById(R.id.spiner);
    btncancal= findViewById(R.id.btncacal);

    btncancal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvdate.setText("");
            edtmakt.setText("");
            edtnote.setText("");
            edttenkt.setText("");
            edttien.setText("");
        }
    });

}

private void Spinner(){
        Vi vi;
        namevis= new ArrayList<>();
viDao= new ViDao(AddKTActivity.this);
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
private void add(){;
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thu thu = new Thu();
                ThuDAO thuDAO= new ThuDAO(AddKTActivity.this);
                thu.namethu = edttenkt.getText().toString();
                thu.tenvi= tenvi;
                thu.sotien =edttien.getText().toString();
                thu.date= tvdate.getText().toString();
                thu.mathu = edtmakt.getText().toString();
                thu.note = edtnote.getText().toString();
                if (thu.mathu.matches("")) {
                    edtmakt.setError("Không được nhập trống");
                } else if (thu.namethu.matches("")) {
                    edttenkt.setError("Không được nhập trống");
                }  else if (thu.sotien.matches("")) {
                    edttien.setError("Không được nhập trống");
                }  else if (thu.tenvi.matches("")) {
                    Toast.makeText(AddKTActivity.this, "Phải chọn ví", Toast.LENGTH_SHORT).show();
                }  else if (thu.date.matches("")) {
                    tvdate.setError("Không được nhập trống");
                }   else if (thu.note.matches("")) {
                    edtnote.setError("Không được nhập trống");
                } else {
                    thuDAO.insertThu(thu);
                    Toast.makeText(AddKTActivity.this, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddKTActivity.this,ThuActivity.class));
                }
            }
        });
}

}
