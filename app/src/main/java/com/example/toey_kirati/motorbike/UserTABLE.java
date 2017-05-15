package com.example.toey_kirati.motorbike;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by TOEY_KIRATI on 15/5/2560.
 */

public class UserTABLE {
    private Mysqlite objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String USER_TABLE = "UserTABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "User";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_NAME = "Name";


    public UserTABLE(Context context){
        objMySQLiteOpenHelper = new Mysqlite(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public String[] searchUser(String struser){
        try{
            String[] strResult = null;
            Cursor cursor = readSqLiteDatabase.query(USER_TABLE, new String[]{ COLUMN_ID, COLUMN_USER},
                    COLUMN_USER + "?", new String[]{String.valueOf(struser)},
                    null,null,null,null);
            if (cursor !=null){
                strResult = new String[2];
                strResult[0] = cursor.getString(0);
                strResult[1] = cursor.getString(1);
            }
            cursor.close();
            return strResult;
        }catch (Exception e){
            return null;
        }

    }



    public long addNewUser(String strUser,String strPassword,String strName){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER,strUser);
        objContentValues.put(COLUMN_PASSWORD,strPassword);
        objContentValues.put(COLUMN_NAME,strName);


        return writeSqLiteDatabase.insert(USER_TABLE,null, objContentValues);
    }




}
