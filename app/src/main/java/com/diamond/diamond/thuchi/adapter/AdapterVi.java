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
import com.diamond.diamond.thuchi.activity.MainActivity;
import com.diamond.diamond.thuchi.activity.ViActivity;
import com.diamond.diamond.thuchi.model.Chi;
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.model.Vi;
import com.diamond.diamond.thuchi.sqldao.ChiDAO;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;
import com.diamond.diamond.thuchi.sqldao.ViDao;

import java.util.ArrayList;
import java.util.List;

public class AdapterVi extends RecyclerView.Adapter<AdapterVi.ViewHolder> {
    private EditText edtname;
    public Context context;
    TextView  edtma;
    int b, tongchi = 0,tong=0;
    int a, tongthu = 0;
    ArrayList<Integer> integers = new ArrayList<>();
    ArrayList<Integer> integerss = new ArrayList<>();
    private ThuDAO thuDao;
    private ChiDAO chiDAO;
    private List<Chi> chis;
    private List<Thu> thus;
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
              tongthu();



             viewHolder.vi=vis.get(i);
//        viDao = new ViDao(context);
//        Vi vi = new Vi();
//        vi.mavi = viewHolder.vi.mavi;
//        vi.tenvi = viewHolder.vi.tenvi;
//        vi.tongthu= String.valueOf(tongthu);
//        vi.tongchi= String.valueOf(tongchi);
//        viDao.updateVi(vi);
//        notifyDataSetChanged();

             viewHolder.tvnamevi.setText(viewHolder.vi.tenvi);
             viewHolder.tvsotien.setText(String.valueOf(tong));



viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(context, InforViActivity.class);
intent.putExtra("ma",viewHolder.vi.mavi);
intent.putExtra("name",viewHolder.vi.tenvi);
intent.putExtra("tongthu",viewHolder.vi.tongthu);
intent.putExtra("tongchi",viewHolder.vi.tongchi);
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
                vi.tongthu= String.valueOf(tongthu);
                vi.tongchi= String.valueOf(tongchi);
                viDao.updateVi(vi);

                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViActivity.class);
                context.startActivity(intent);
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
    private void tongthu() {
        Thu thu;
        thuDao = new ThuDAO(context);
        thus = thuDao.getALLThu();
        for (int i = 0; i < thus.size(); i++) {
            thu = thus.get(i);
            a = Integer.parseInt(thu.date);
            integers.add(a);
        }
        for (Integer element : integers) {
            tongthu += element;

        }
        Chi chi;
        chiDAO = new ChiDAO(context);
        chis= chiDAO.getALLChi();
        for (int j =0; j<chis.size(); j ++){
            chi = chis.get(j);
            b = Integer.parseInt(chi.sotien);
            integerss.add(b);
        }
        for (Integer element : integerss) {
            tongchi += element;

        }

        tong = tongthu- tongchi;
    }
}
