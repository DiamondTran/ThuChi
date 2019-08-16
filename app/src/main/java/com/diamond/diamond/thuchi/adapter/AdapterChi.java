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
import com.diamond.diamond.thuchi.activity.UpdattChiActivity;
import com.diamond.diamond.thuchi.model.Chi;
import com.diamond.diamond.thuchi.sqldao.ChiDAO;

import java.util.ArrayList;
import java.util.List;

public class AdapterChi extends RecyclerView.Adapter<AdapterChi.ViewHolder> {
    private Spinner sp;
    private ChiDAO chiDAO;

    private Context context;
    private List<Chi> chis;
    private List<Chi> chiss;
    public AdapterChi(Context context, List<Chi> chis) {
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
        viewHolder.tvtenvi.setText("Tiết kiệm");

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InforChiActivity.class);
                intent.putExtra("machi", viewHolder.chi.machi);
                intent.putExtra("tenchi", viewHolder.chi.namechi);
                intent.putExtra("sotien", viewHolder.chi.sotien);
                intent.putExtra("ngay", viewHolder.chi.date);
                intent.putExtra("sovi", viewHolder.tvtenvi.getText());
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
             Intent intent= new Intent(context, UpdattChiActivity.class);
                intent.putExtra("machi", viewHolder.chi.machi);
                intent.putExtra("tenchi", viewHolder.chi.namechi);
                intent.putExtra("sotien", viewHolder.chi.sotien);
             intent.putExtra("ngay",viewHolder.chi.date);
                intent.putExtra("note", viewHolder.chi.note);
             context.startActivity(intent);

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
        public Chi chi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                tvtenchi= itemView.findViewById(R.id.namechi);
                tvtenvi = itemView.findViewById(R.id.namevi);
                tvsotien = itemView.findViewById(R.id.sotien);
                tvngaythang = itemView.findViewById(R.id.datechi);
                imgdele= itemView.findViewById(R.id.imgdelete);
                imgedit= itemView.findViewById(R.id.imgeditc);
            cardView = itemView.findViewById(R.id.cradviewchi);

        }
    }
}
