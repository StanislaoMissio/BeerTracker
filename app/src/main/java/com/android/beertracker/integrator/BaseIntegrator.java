package com.android.beertracker.integrator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class BaseIntegrator {

    protected ApplicationDataBaseHelper mApplicationDataBaseHelper;
    protected SQLiteDatabase database;

    public BaseIntegrator() {
    }

    public BaseIntegrator(Context context) {
        this.mApplicationDataBaseHelper = new ApplicationDataBaseHelper(context);
        this.database = this.mApplicationDataBaseHelper.getWritableDatabase();

    }

    protected void openDatabase() {
        if (!this.database.isOpen()) {
            this.database = this.mApplicationDataBaseHelper.getWritableDatabase();
        }

    }

    protected void releaseDatabase() {
        if (this.database != null && this.database.isOpen()) {
            database.close();
        }

    }
}
