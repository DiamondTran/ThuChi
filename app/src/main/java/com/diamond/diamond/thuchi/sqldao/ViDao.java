package com.diamond.diamond.thuchi.sqldao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diamond.diamond.thuchi.database.DBHelper;
import com.diamond.diamond.thuchi.model.Vi;

import java.util.ArrayList;
import java.util.List;

import static com.diamond.diamond.thuchi.Constans.VI_MA;
import static com.diamond.diamond.thuchi.Constans.VI_TONG_CHI;
import static com.diamond.diamond.thuchi.Constans.VI_TONG_THU;
import static com.diamond.diamond.thuchi.Constans.VI_NAME;
import static com.diamond.diamond.thuchi.Constans.VI_TABLE;

public class ViDao {
    private DBHelper dbHelper;
    public ViDao(Context context){
        this.dbHelper= new DBHelper(context);
    }

    public long insertVi(Vi vi) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(VI_MA, vi.mavi);
        contentValues.put(VI_NAME, vi.tenvi);
        contentValues.put(VI_TONG_THU, vi.tongthu);
        contentValues.put(VI_TONG_CHI, vi.tongchi);


        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        result = sqLiteDatabase.insert(VI_TABLE, null, contentValues);

        sqLiteDatabase.close();

        return result;
    }

    public long updateVi(Vi vi){
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(VI_MA, vi.mavi);
        contentValues.put(VI_NAME, vi.tenvi);
        contentValues.put(VI_TONG_THU, vi.tongthu);
        contentValues.put(VI_TONG_CHI, vi.tongchi);


        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        result = sqLiteDatabase.update(VI_TABLE, contentValues, VI_MA + "=?",
                new String[]{vi.mavi});

        sqLiteDatabase.close();

        return result;
    }

    public long deleteVi(String mavi) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(VI_TABLE, VI_MA + "=?",
                new String[]{mavi});
        return result;
    }

    public List<Vi> getALLVi() {
        List<Vi> vis = null;
        String QUERY = "SELECT * FROM " + VI_TABLE;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);

        if (cursor != null) {
            vis = new ArrayList<>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String mavi = cursor.getString(cursor.getColumnIndex(VI_MA));
                String namevi = cursor.getString(cursor.getColumnIndex(VI_NAME));
                String tongthu = cursor.getString(cursor.getColumnIndex(VI_TONG_THU));
                String tongchi = cursor.getString(cursor.getColumnIndex(VI_TONG_CHI));

                Vi vi = new Vi();
                vi.mavi = mavi;
                vi.tenvi = namevi;
                vi.tongthu = tongthu;
                vi.tongchi = tongchi;


                vis.add(vi);
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return vis;

    }
}
