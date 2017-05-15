package com.example.toey_kirati.motorbike;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by TOEY_KIRATI on 15/5/2560.
 */

public class OrderTABLE {
        private Mysqlite objMySQLiteOpenHelper;
        private SQLiteDatabase writeSqLiteDatabase;
        private SQLiteDatabase readSqLiteDatabase;

        public static final String ORDER_TABLE = "OrderTABLE";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_OFFICER = "officer";
        public static final String COLUMN_BIL = "Bike";
        public static final String COLUMN_ITEM = "Item";



        public OrderTABLE(Context context){
            objMySQLiteOpenHelper = new Mysqlite(context);
            writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
            readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
        }

        public long addNewOrder(String strBike, String strItem, String strofficer){
            ContentValues objContentValues = new ContentValues();
            objContentValues.put(COLUMN_OFFICER,strofficer);
            objContentValues.put(COLUMN_BIL,strBike);
            objContentValues.put(COLUMN_ITEM,strItem);


            return writeSqLiteDatabase.insert(ORDER_TABLE,null, objContentValues);
        }



 }