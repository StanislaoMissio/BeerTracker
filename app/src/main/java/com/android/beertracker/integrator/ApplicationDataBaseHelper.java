package com.android.beertracker.integrator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.beertracker.table.EstiloContract;

public class ApplicationDataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "WorldWonder.db";

    public ApplicationDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstiloContract.EstiloEntry.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + PlaceContract.PlaceEntry.TABLE_NAME);
//        onCreate(db);
    }
}
