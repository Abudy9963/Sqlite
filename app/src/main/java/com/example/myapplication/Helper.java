package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "main.db";
    public static final String TABLE_NAME = "CONTACT";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "model";
    public static final String COL_4 = "number";


    public Helper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,MODEL TEXT,NUMBER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, car.getName());
        contentValues.put(COL_3, car.getModel());
        contentValues.put(COL_4, car.getNumber());


        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Car> getall() {
        ArrayList<Car> cars = new ArrayList<Car>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select* from " + TABLE_NAME + " ", null);
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                String model = c.getString(2);
                String number = c.getString(3);
                Car car = new Car(name, model, number);
                cars.add(car);

            }
            while (c.moveToNext());
            c.close();
        }

        return cars;
    }

    public boolean update(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, car.getName());
        contentValues.put(COL_3, car.getModel());
        contentValues.put(COL_4, car.getNumber());

        String[] args = {car.getNumber()};

        int result = db.update(TABLE_NAME, contentValues, "NUMBER=?", args);
        if (result > 0) ;
        return true;


    }


    public int DeleteData(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {car.getNumber()};
        int result = db.delete(TABLE_NAME, "NUMBER=?", args);

        if (result > 0) ;
        return result;

    }
}