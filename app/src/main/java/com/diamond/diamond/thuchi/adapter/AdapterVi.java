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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.activity.InforViActivity;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.util.List;

public class AdapterVi extends RecyclerView.Adapter<AdapterVi.ViewHolder> {
    private EditText edtma,edtname;
    public Context context;
    public List<Vi> vis;
    ViDao viDao;

    public AdapterVi(Context context, List<Vi> vis) {
        this.context = context;
        this.vis = vis;
    }

    @NonNull
    @Override
    public AdapterVi.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_vi,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterVi.ViewHolder viewHolder, final int i) {
             viewHolder.vi=vis.get(i);
             viewHolder.tvnamevi.setText(viewHolder.vi.tenvi);


viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(context, InforViActivity.class);
intent.putExtra("ma",viewHolder.vi.mavi);
intent.putExtra("name",viewHolder.vi.tenvi);
intent.putExtra("tongthu",viewHolder.vi.tongthu);
context.startActivity(intent);

    }
});
viewHolder.imgdelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final AlertDialog.Builder builder  = new AlertDialog.Builder(context);
        builder.setTitle("Message");
        builder.setMessage("Bạn có muốn xóa ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               viDao = new ViDao(context);
                viDao.deleteVi(viewHolder.vi.mavi);
                vis.remove(i);
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
        View viewDialog = inflater.inflate(R.layout.edit_vi, null);
        edtma= viewDialog.findViewById(R.id.edtmavi);
        edtname= viewDialog.findViewById(R.id.edttenvi);


        edtma.setText(vis.get(i).mavi);
        edtname.setText(vis.get(i).tenvi);



        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                viDao = new ViDao(context);
                Vi vi = new Vi();
                vi.mavi= edtma.getText().toString();
                vi.tenvi = edtname.getText().toString();

                viDao.updateVi(vi);

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

        if (vis == null) return 0;
        else
            return vis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvnamevi;
        public TextView tvsotien;
        private ImageView imgedit,imgdelete;
        public CardView cardView;
        public Vi vi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
     tvnamevi= itemView.findViewById(R.id.namevi);
     tvsotien = itemView.findViewById(R.id.sotien);
     cardView = itemView.findViewById(R.id.craviewvi);
     imgdelete = itemView.findViewById(R.id.imgdelete);
     imgedit= itemView.findViewById(R.id.imgedit);
        }
    }
}
