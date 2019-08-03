package com.diamond.diamond.thuchi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.activity.InforChiActivity;
import com.diamond.diamond.thuchi.model.chi;
import com.diamond.diamond.thuchi.model.thu;
import com.diamond.diamond.thuchi.sqldao.ChiDAO;

import java.util.ArrayList;
import java.util.List;

public class AdapterChi extends RecyclerView.Adapter<AdapterChi.ViewHolder> {
    private Spinner sp;
    private ChiDAO chiDAO;
    private TextView tvngay;
    private EditText edtmat,edtnamet,edttient,edtnott;
    private List<String> namechis;
    private Context context;
    private List<chi> chis;
    private List<chi> chiss;
    public AdapterChi(Context context, List<chi> chis) {
        this.context = context;
        this.chis = chis;
    }

    @NonNull
    @Override
    public AdapterChi.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_chi,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterChi.ViewHolder viewHolder, final int i) {
        viewHolder.chi = chis.get(i);
        viewHolder.tvtenchi.setText(viewHolder.chi.namechi);
        viewHolder.tvtenvi.setText(viewHolder.chi.tenvi);
        viewHolder.tvsotien.setText(viewHolder.chi.sotien);
        viewHolder.tvngaythang.setText(viewHolder.chi.date);


        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InforChiActivity.class);
                intent.putExtra("machi", viewHolder.chi.machi);
                intent.putExtra("tenthu", viewHolder.chi.namechi);
                intent.putExtra("sotien", viewHolder.chi.sotien);
                intent.putExtra("ngay", viewHolder.chi.date);
                intent.putExtra("sovi", viewHolder.chi.tenvi);
                intent.putExtra("note", viewHolder.chi.note);
                context.startActivity(intent);
            }
        });

        viewHolder.imgdele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Message");
                builder.setMessage("Bạn có muốn xóa ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chiDAO = new ChiDAO(context);
                        chiDAO.deleteChi(viewHolder.chi.machi);
                        chis.remove(i);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
        viewHolder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cập nhật");
                final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.activity_add_kt, null);
                edtmat = viewDialog.findViewById(R.id.edtmakt);
                edtnamet = viewDialog.findViewById(R.id.edtnamekt);
                edtnott = viewDialog.findViewById(R.id.edtnote);
                edttient = viewDialog.findViewById(R.id.edtsotien);
                sp= viewDialog.findViewById(R.id.spiner);
                tvngay= viewDialog.findViewById(R.id.edtdatet);



                edtmat.setText(chis.get(i).machi);
                edtnamet.setText(chis.get(i).namechi);
                edttient.setText(chis.get(i).sotien);
                edtnott.setText(chis.get(i).note);

                tvngay.setText(chis.get(i).date);


                builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        chiDAO = new ChiDAO(context);
                        final chi chi = new chi();
                        chi.machi = edtmat.getText().toString();
                        chi.namechi= edtnamet.getText().toString();
                        chi.sotien= edttient.getText().toString();
                        chi.note = edtnott.getText().toString();
                        chi.date = tvngay.getText().toString();

                        namechis= new ArrayList<>();
                        chi chi1;
                        chiDAO= new ChiDAO(context);
                        chiss= chiDAO.getALLChi();
                        for (int i=0;i<chiss.size();i++){
                            chi1= chiss.get(i);
                            namechis.add(chi1.tenvi);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter(context,android.R.layout.simple_spinner_item,namechis);
                        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                        sp.setAdapter(adapter);
                        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                chi.tenvi= sp.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        chiDAO.updateChi(chi);

                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                        notifyDataSetChanged();
                    }
                });
                builder.setView(viewDialog);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (chis == null) return 0;
        else
            return chis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtenchi, tvtenvi, tvsotien, tvngaythang;
        public ImageView imgedit, imgdele;
        public CardView cardView;
        public chi chi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                tvtenchi= itemView.findViewById(R.id.namechi);
                tvtenvi = itemView.findViewById(R.id.namevi);
                tvsotien = itemView.findViewById(R.id.sotien);
                tvngaythang = itemView.findViewById(R.id.datechi);
                imgdele= itemView.findViewById(R.id.imgdelete);
                imgedit= itemView.findViewById(R.id.imgedit);
            cardView = itemView.findViewById(R.id.cradviewchi);

        }
    }
}
