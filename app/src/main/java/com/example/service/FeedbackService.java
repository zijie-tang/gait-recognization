package com.example.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.Bean.Feedback;

public class FeedbackService {
    private DatabaseHelper dbHelper;
    public FeedbackService(Context context){
        dbHelper=new DatabaseHelper(context);
    }
    public boolean add(Feedback feedback){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into feedback(UserId,Info) values(?,?)";
        Object obj[]={feedback.getUserId(),feedback.getInfo()};
        sdb.execSQL(sql, obj);
        return true;
    }
}
