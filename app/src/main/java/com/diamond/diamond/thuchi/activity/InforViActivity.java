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
import com.diamond.diamond.thuchi.model.Chi;
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ChiDAO;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.util.ArrayList;
import java.util.List;

public class InforViActivity extends AppCompatActivity {
    private TextView tvma, tvname, tvtongthu, tvtongchi;
    String ma, name;
    private ThuDAO thuDao;
    private ChiDAO chiDAO;
    private List<Chi> chis;
    private List<Thu> thus;
    ViDao viDao;
    int b, tongchi = 0, tong = 0;
    int a, tongthu = 0;
    ArrayList<Integer> integers = new ArrayList<>();
    ArrayList<Integer> integerss = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_vi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        thongtin();
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
        viDao = new ViDao(InforViActivity.this);
        Vi vi = new Vi();
        vi.mavi = ma;
        vi.tenvi =name;
        vi.tongthu = String.valueOf(tongthu);
        vi.tongchi = String.valueOf(tongchi);

        viDao.updateVi(vi);

        tvma.setText(ma);
        tvname.setText(name);

tvtongthu.setText(String.valueOf(tongthu));
tvtongchi.setText(String.valueOf(tongchi));



    }
private void thongtin(){
    Thu thu;
    thuDao = new ThuDAO(InforViActivity.this);
    thus = thuDao.getALLThu();
    for (int i = 0; i < thus.size(); i++) {
        thu = thus.get(i);
        a = Integer.parseInt(thu.date);
        integers.add(a);
    }

    Chi chi;
    chiDAO = new ChiDAO(InforViActivity.this);
    chis= chiDAO.getALLChi();
    for (int j =0; j<chis.size(); j ++){
        chi = chis.get(j);
        b = Integer.parseInt(chi.sotien);
        integerss.add(b);
    }
    for (Integer element : integerss) {
        tongchi += element;

    }
    for (Integer element : integers) {
        tongthu += element;

    }
    tong = tongthu- tongchi;

}

}
