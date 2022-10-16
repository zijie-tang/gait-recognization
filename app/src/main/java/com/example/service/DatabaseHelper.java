package com.example.service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    static String name="gait.db";
    static int dbVersion=3;
    public DatabaseHelper(Context context) {
        super(context, name, null, dbVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql1="create table user(id integer primary key autoincrement,username varchar(20),password varchar(20))";
        String sql2="create table ACCCollection(id integer primary key autoincrement,Timestamp varchar(20),Acc_x varchar(20),Acc_y varchar(20),Acc_z varchar(20),UserId integer)";
        String sql3="create table feedback(id integer primary key autoincrement,userId integer,Info varchar(200))";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
