package com.example.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.Bean.ACC_info;

public class CollectService {
    private DatabaseHelper dbHelper;
    public CollectService(Context context){
        dbHelper=new DatabaseHelper(context);
    }
    public boolean add(ACC_info acc_info){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into ACCCollection(Timestamp,Acc_x,Acc_y,Acc_z,UserId) values(?,?,?,?,?)";
        Object obj[]={acc_info.getTimestamp(),acc_info.getAcc_x(),acc_info.getAcc_y(),acc_info.getAcc_z(),acc_info.getUserId()};
        sdb.execSQL(sql, obj);
        return true;
    }
}
