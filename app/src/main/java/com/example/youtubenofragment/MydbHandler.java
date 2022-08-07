package com.example.youtubenofragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MydbHandler extends SQLiteOpenHelper {
    public MydbHandler(@Nullable Context context) {
        super(context, "YoutubePlaylist", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table YTPlaylist(name text primary key,descr text,imageid int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists YTPlaylist");
        onCreate(db);

    }
    public Boolean insertuserdata(String name,String descr,int imageid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("descr",descr);
        contentValues.put("imageid",imageid);

        long result = db.insert("YTPlaylist",null,contentValues);
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from YTPlaylist",null);
        return cursor;
    }

}
