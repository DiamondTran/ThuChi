package com.diamond.diamond.thuchi.sqldao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diamond.diamond.thuchi.database.DBHelper;
import com.diamond.diamond.thuchi.model.Thu;

import java.util.ArrayList;
import java.util.List;

import static com.diamond.diamond.thuchi.Constans.THU_DATE;
import static com.diamond.diamond.thuchi.Constans.THU_GHI_CHU;
import static com.diamond.diamond.thuchi.Constans.THU_MA;
import static com.diamond.diamond.thuchi.Constans.THU_NAME;
import static com.diamond.diamond.thuchi.Constans.THU_TABLE;
import static com.diamond.diamond.thuchi.Constans.THU_TEN_VI;
import static com.diamond.diamond.thuchi.Constans.THU_TIEN;

public class ThuDAO {
    private DBHelper dbHelper;

    public ThuDAO(Context context) {
        this.dbHelper = new DBHelper(context);

    }

    public long insertThu(Thu thu) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(THU_MA, thu.mathu);
        contentValues.put(THU_NAME, thu.namethu);
        contentValues.put(THU_TIEN, thu.sotien);
        contentValues.put(THU_DATE, thu.date);
        contentValues.put(THU_TEN_VI, thu.tenvi);
        contentValues.put(THU_GHI_CHU, thu.note);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        result = sqLiteDatabase.insert(THU_TABLE, null, contentValues);

        sqLiteDatabase.close();

        return result;
    }

    public long updateThu(Thu thu) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(THU_MA, thu.mathu);
        contentValues.put(THU_NAME, thu.namethu);
        contentValues.put(THU_TIEN, thu.sotien);
        contentValues.put(THU_DATE, thu.date);
        contentValues.put(THU_TEN_VI, thu.tenvi);
        contentValues.put(THU_GHI_CHU, thu.note);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        result = sqLiteDatabase.update(THU_TABLE, contentValues, THU_MA + "=?",
                new String[]{thu.mathu});

        sqLiteDatabase.close();

        return result;
    }

    public long deleteThu(String mathu) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(THU_TABLE, THU_MA + "=?",
                new String[]{mathu});
        return result;
    }

    public List<Thu> getALLThu() {
        List<Thu> thus = null;
        String QUERY = "SELECT * FROM " + THU_TABLE;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);

        if (cursor != null) {
            thus = new ArrayList<>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String mathu = cursor.getString(cursor.getColumnIndex(THU_MA));
                String namethu = cursor.getString(cursor.getColumnIndex(THU_NAME));
                String tienthu = cursor.getString(cursor.getColumnIndex(THU_TIEN));
                String datethu = cursor.getString(cursor.getColumnIndex(THU_DATE));
                String vithu = cursor.getString(cursor.getColumnIndex(THU_TEN_VI));
                String notethu = cursor.getString(cursor.getColumnIndex(THU_GHI_CHU));
                Thu thu = new Thu();
                thu.mathu = mathu;
                thu.namethu = namethu;
                thu.date = tienthu;
                thu.sotien = datethu;
                thu.tenvi = vithu;
                thu.note = notethu;

                thus.add(thu);
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return thus;

    }
}
