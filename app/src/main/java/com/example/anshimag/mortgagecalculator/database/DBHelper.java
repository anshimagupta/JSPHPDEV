package com.example.anshimag.mortgagecalculator.database;
/**
 * This class is for operations in the DB. Each new
 * calculated mortgage value is updated to the SQL Lite
 * DB.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.anshimag.mortgagecalculator.model.Mortgage;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Mortgage.db";
    public static final String MORTGAGE_TABLE_NAME = "mortgage";
    public static final String MORTGAGE_COLUMN_ID = "id";
    public static final String MORTGAGE_COLUMN_MONTHLY_PAYMENT = "monthly_payment";
    public static final String MORTGAGE_COLUMN_TOTAL_PAYMENT = "total_payment";
    public static final String MORTGAGE_COLUMN_PAY_OFF_DATE = "pay_off_date";
    private static DBHelper instance = null;

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }

        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MORTGAGE_TABLE_NAME +
                        "(" + MORTGAGE_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        MORTGAGE_COLUMN_MONTHLY_PAYMENT + " text, " +
                        MORTGAGE_COLUMN_TOTAL_PAYMENT + " text, " + MORTGAGE_COLUMN_PAY_OFF_DATE +
                        " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    /*
    Add the Mortgage monthly value, total value and pay off year to DB
    */
    public void insertMortgage(Mortgage mortgage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MORTGAGE_COLUMN_MONTHLY_PAYMENT, mortgage.getMonthlyPayment());
        contentValues.put(MORTGAGE_COLUMN_TOTAL_PAYMENT, mortgage.getTotalPayment());
        contentValues.put(MORTGAGE_COLUMN_PAY_OFF_DATE, mortgage.getPayOffDate());
        db.insert(MORTGAGE_TABLE_NAME, null, contentValues);
    }
    /*
    This method returns the mortgage object
     */
    public List<Mortgage> getAllMortgages() {
        List<Mortgage> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        String resultQuery = "select * from " + MORTGAGE_TABLE_NAME;
        Cursor result =  db.rawQuery( resultQuery , null );
        result.moveToFirst();
        while(!result.isAfterLast()) {
            Mortgage mortgage = new Mortgage(result.getString(
                    result.getColumnIndex(MORTGAGE_COLUMN_MONTHLY_PAYMENT)),
                    result.getString(result.getColumnIndex(MORTGAGE_COLUMN_TOTAL_PAYMENT)),
                    result.getString(result.getColumnIndex(MORTGAGE_COLUMN_PAY_OFF_DATE)));
            list.add(mortgage);
            result.moveToNext();
        }
        result.close();

        return list;
    }
}
