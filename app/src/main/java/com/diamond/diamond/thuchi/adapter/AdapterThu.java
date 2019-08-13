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
import com.diamond.diamond.thuchi.activity.InforthuActivity;
import com.diamond.diamond.thuchi.activity.UpdatethuActivity;
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;

import java.util.ArrayList;
import java.util.List;

public class AdapterThu extends RecyclerView.Adapter<AdapterThu.ViewHolder> {
    private Context context;
    private List<Thu> thus;
    private ThuDAO thuDAO;


    public AdapterThu(Context context, List<Thu> thus) {
        this.context = context;
        this.thus = thus;
    }

    @NonNull
    @Override
    public AdapterThu.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thu, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterThu.ViewHolder viewHolder, final int i) {
        viewHolder.thu = thus.get(i);
        viewHolder.tvtenthu.setText(viewHolder.thu.namethu);
        viewHolder.tvtenvi.setText(viewHolder.thu.tenvi);
        viewHolder.tvsotien.setText(viewHolder.thu.sotien);
        viewHolder.tvngaythang.setText(viewHolder.thu.date);


        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InforthuActivity.class);
                intent.putExtra("mathu", viewHolder.thu.mathu);
                intent.putExtra("tenthu", viewHolder.thu.namethu);
                intent.putExtra("sotien", viewHolder.thu.sotien);
                intent.putExtra("ngay", viewHolder.thu.date);
                intent.putExtra("sovi", viewHolder.thu.tenvi);
                intent.putExtra("note", viewHolder.thu.note);
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
                        thuDAO = new ThuDAO(context);
                        thuDAO.deleteThu(viewHolder.thu.mathu);
                        thus.remove(i);
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
                      Intent intent= new Intent(context, UpdatethuActivity.class);
                intent.putExtra("mathu", viewHolder.thu.mathu);
                intent.putExtra("tenthu", viewHolder.thu.namethu);
                intent.putExtra("sotien", viewHolder.thu.sotien);
                intent.putExtra("ngay", viewHolder.thu.date);
                intent.putExtra("note", viewHolder.thu.note);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (thus == null) return 0;
        else
            return thus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvtenthu, tvtenvi, tvsotien, tvngaythang;
        public ImageView imgedit, imgdele;
        public CardView cardView;
        public Thu thu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtenthu = itemView.findViewById(R.id.namethu);
            tvtenvi = itemView.findViewById(R.id.namevi);
            tvsotien = itemView.findViewById(R.id.sotien);
            tvngaythang = itemView.findViewById(R.id.tvdate);
            imgedit = itemView.findViewById(R.id.imgedit);
            imgdele = itemView.findViewById(R.id.imgdelete);
            cardView = itemView.findViewById(R.id.cradviewthu);

        }
    }
}
