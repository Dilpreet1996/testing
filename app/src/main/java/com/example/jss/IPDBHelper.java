package com.example.jss;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.jss.ipcontent.*;

public class IPDBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="databaseofIP.db";
    public static  final int DATABASE_VERSION=1;


    public IPDBHelper(Context context)

    {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String SQL_CREATE_IPTABLE="CREATE TABLE "+ ipcontentEntry.TABLE_NAME +" (" +ipcontentEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ipcontentEntry.COLUMN_NAME + " TEXT NOT NULL," + ipcontentEntry.COLUMN_TIMESTAMP +" TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +");";
        db.execSQL(SQL_CREATE_IPTABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + ipcontentEntry.TABLE_NAME);
        onCreate(db);

    }
}