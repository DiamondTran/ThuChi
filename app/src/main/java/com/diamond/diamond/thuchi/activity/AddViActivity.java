package com.diamond.diamond.thuchi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.util.List;

public class AddViActivity extends AppCompatActivity {
    private EditText edtnamevi, edtmavi;
    private Button btnadd, btnhuy;
    private ViDao viDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtmavi = findViewById(R.id.edtmavi);
        edtnamevi = findViewById(R.id.edtnamevi);
        btnadd = findViewById(R.id.btnadd);
        btnhuy = findViewById(R.id.btncacal);


        add();
    }

    private void add() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vi vi = new Vi();
                viDao = new ViDao(AddViActivity.this);
                vi.mavi = edtmavi.getText().toString();
                vi.tenvi = edtnamevi.getText().toString();
                if (vi.mavi.matches("")) {
                    edtmavi.setError("Không được nhập trống");
                } else if (vi.tenvi.matches("")) {
                    edtnamevi.setError("Không được nhập trống");
                } else {
                    viDao.insertVi(vi);

                    Toast.makeText(AddViActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddViActivity.this,ViActivity.class));
                }
            }
        });
    }

}
