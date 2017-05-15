package com.example.toey_kirati.motorbike;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TOEY_KIRATI on 15/5/2560.
 */

public class Mysqlite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bike.db";//กำหนดชื่อไฟล์ Database
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table UserTABLE"+
            "(_id integer primary key ,User text,Password text,Name text);";
    private static final String CREATE_BIKE_TABLE = "create table BikeTABLE"+
            "(_id integer primary key, _id text,Bike text, Price text );";
    private static final String CREATE_ORDER_TABLE = "create table OrderTABLE"+
            "(_id integer primary key, _id text , officer text, Bike text, Item text);";

    public Mysqlite(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_BIKE_TABLE);
        sqLiteDatabase.execSQL(CREATE_ORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}

