package com.example.medicalhealth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "Data.db";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //sql创建表的语句
        String usersInfo_table = "create table usertable" +
                "(id integer primary key autoincrement, username text," +
                "password text)";
        // 创建表
        db.execSQL(usersInfo_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("此app","--版本更新"+oldVersion+"-->"+newVersion);
        db.execSQL("alter table usertable add column other string");
    }
}
