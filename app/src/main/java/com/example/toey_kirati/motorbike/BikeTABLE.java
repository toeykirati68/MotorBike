package com.example.toey_kirati.motorbike;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by TOEY_KIRATI on 15/5/2560.
 */

public class BikeTABLE {
    private Mysqlite objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String BIKE_TABLE = "BikeTABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BIKE = "Bike";
    public static final String COLUMN_PRICE = "Price";


    public BikeTABLE(Context context){
        objMySQLiteOpenHelper = new Mysqlite(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long addNewBike(String strBike,String strPrice){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_BIKE,strBike);
        objContentValues.put(COLUMN_PRICE,strPrice);

        return writeSqLiteDatabase.insert(BIKE_TABLE,null, objContentValues);
    }

    public String[] readAllFood (int intColumn){
        String[] strReadALL = null;
        Cursor cursor = readSqLiteDatabase.query(BIKE_TABLE,new String[]{COLUMN_ID, COLUMN_BIKE,COLUMN_PRICE}, null, null , null, null, null);

        if (cursor != null);
        cursor.moveToFirst();
        strReadALL = new String[cursor.getCount()];
        for (int i = 0; i<= cursor.getCount(); i++){
            switch (intColumn){
                case 1:
                    strReadALL[i] = cursor.getString(cursor.getColumnIndex(COLUMN_BIKE));
                    break;

                default:
                    strReadALL[i] = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE));
                    break;
            }
            cursor.moveToNext();
        }
        return strReadALL;
    }
}

