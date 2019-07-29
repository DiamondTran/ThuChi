package com.diamond.diamond.thuchi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.diamond.diamond.thuchi.Constans.CHI_TABLE;
import static com.diamond.diamond.thuchi.Constans.CREATE_THU_TABLE;
import static com.diamond.diamond.thuchi.Constans.CREATE_CHI_TABLE;
import static com.diamond.diamond.thuchi.Constans.CREATE_VI_TABLE;
import static com.diamond.diamond.thuchi.Constans.THU_TABLE;
import static com.diamond.diamond.thuchi.Constans.VI_TABLE;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper (Context context){
super(context,"thuchi.sql",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_THU_TABLE);
        db.execSQL(CREATE_CHI_TABLE);
        db.execSQL(CREATE_VI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ THU_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ CHI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ VI_TABLE);
        onCreate(db);
    }
}
