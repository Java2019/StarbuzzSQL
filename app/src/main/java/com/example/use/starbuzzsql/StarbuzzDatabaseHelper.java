package com.example.use.starbuzzsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by use on 08.02.17.
 */
 class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzzsql";
    private static final int DB_VERSION = 3;

    public StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        updateMyDatabase(sqLiteDatabase, oldVersion, DB_VERSION);
    }

    public static void insertDrink(SQLiteDatabase db, String name, String description, int resorceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resorceId);
        db.insert("DRINK1", null, drinkValues);
    }

    private void updateMyDatabase(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        if (oldVersion < 1){
            sqLiteDatabase.execSQL("CREATE TABLE DRINK1 (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertDrink(sqLiteDatabase, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertDrink(sqLiteDatabase, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
            insertDrink(sqLiteDatabase, "Filter", "Our best drip coffee", R.drawable.filter);
        };
        if (oldVersion<2){
            sqLiteDatabase.execSQL("ALTER TABLE DRINK1 ADD COLUMN FAVORITE NUMERIC;");
        }
    }
    public void copyFile(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }
 }

