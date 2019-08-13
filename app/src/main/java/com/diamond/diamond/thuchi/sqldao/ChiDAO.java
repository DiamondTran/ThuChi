package com.diamond.diamond.thuchi.sqldao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diamond.diamond.thuchi.database.DBHelper;
import com.diamond.diamond.thuchi.model.Chi;

import java.util.ArrayList;
import java.util.List;

import static com.diamond.diamond.thuchi.Constans.CHI_DATE;
import static com.diamond.diamond.thuchi.Constans.CHI_GHI_CHU;
import static com.diamond.diamond.thuchi.Constans.CHI_MA;
import static com.diamond.diamond.thuchi.Constans.CHI_NAME;
import static com.diamond.diamond.thuchi.Constans.CHI_TABLE;
import static com.diamond.diamond.thuchi.Constans.CHI_TEN_VI;
import static com.diamond.diamond.thuchi.Constans.CHI_TIEN;


public class ChiDAO {
    private DBHelper dbHelper;

    public ChiDAO(Context context) {
        this.dbHelper = new DBHelper(context);

    }

    public long insertChi(Chi chi) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHI_MA, chi.machi);
        contentValues.put(CHI_NAME, chi.namechi);
        contentValues.put(CHI_TIEN, chi.sotien);
        contentValues.put(CHI_DATE, chi.date);
//        contentValues.put(CHI_TEN_VI, chi.tenvi);
        contentValues.put(CHI_GHI_CHU, chi.note);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        result = sqLiteDatabase.insert(CHI_TABLE, null, contentValues);

        sqLiteDatabase.close();

        return result;
    }

    public long updateChi(Chi chi) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHI_MA, chi.machi);
        contentValues.put(CHI_NAME, chi.namechi);
        contentValues.put(CHI_TIEN, chi.sotien);
        contentValues.put(CHI_DATE, chi.date);
//        contentValues.put(CHI_TEN_VI, chi.tenvi);
        contentValues.put(CHI_GHI_CHU, chi.note);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        result = sqLiteDatabase.update(CHI_TABLE, contentValues, CHI_MA + "=?",
                new String[]{chi.machi});

        sqLiteDatabase.close();

        return result;
    }

    public long deleteChi(String machi) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(CHI_TABLE, CHI_MA + "=?",
                new String[]{machi});
        return result;
    }

    public List<Chi> getALLChi() {
        List<Chi> chis = null;
        String QUERY = "SELECT * FROM " + CHI_TABLE;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);

        if (cursor != null) {
            chis = new ArrayList<>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String machi = cursor.getString(cursor.getColumnIndex(CHI_MA));
                String namechi = cursor.getString(cursor.getColumnIndex(CHI_NAME));
                String tienchi = cursor.getString(cursor.getColumnIndex(CHI_TIEN));
                String datechi = cursor.getString(cursor.getColumnIndex(CHI_DATE));
//                String vichi = cursor.getString(cursor.getColumnIndex(CHI_TEN_VI));
                String notechi = cursor.getString(cursor.getColumnIndex(CHI_GHI_CHU));
                Chi chi = new Chi();
                chi.machi = machi;
                chi.namechi = namechi;
                chi.date = datechi;
                chi.sotien = tienchi;
//                chi.tenvi= vichi;
                chi.note = notechi;
                chis.add(chi);
                cursor.moveToNext();

            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return chis;

    }


}
