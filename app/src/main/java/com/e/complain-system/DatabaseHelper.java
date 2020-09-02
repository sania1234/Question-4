package com.lab5.complain_recording_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "complain_database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(name text, email text primary key, password text)");
        db.execSQL("Create table complain_table(Id text primary key, Category text , Severity text, Description text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists complain_table");
        onCreate(db);
    }

    public boolean insert_comp(String comp, String category_comp, String sever, String descr){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values_comp = new ContentValues();
        values_comp.put("Id",comp);
        values_comp.put("Category",category_comp);
        values_comp.put("Severity",sever);
        values_comp.put("Description",descr);
        sqLiteDatabase.insert("complain_table", null, values_comp);
        return true;
            }
//    public boolean insert_comp(int id1, String category, String severity, String description) {
//    }
    public boolean insert(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email",email);
        contentValues.put("password" ,password);
        long ins = db.insert("user", null, contentValues);
        if(ins==-1) return false;
        else return true;
    }



    //checking if email exists
    public Boolean chkemial(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery("Select * from user where email =?", new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    //checking the email and password
    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }



}
