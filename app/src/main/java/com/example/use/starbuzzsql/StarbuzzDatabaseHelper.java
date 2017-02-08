package com.example.use.starbuzzsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by use on 08.02.17.
 */
 class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzzsql";
    private static final int DB_VERSION = 2;

    public StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public static void insertDrink(SQLiteDatabase db, String name, String description, int resorceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resorceId);
        db.insert("DRINK", null, drinkValues);
    }

    private void updateMyDatabase(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        if (oldVersion < 1){
            sqLiteDatabase.execSQL("CREATE TABLE DRINK ("
                    + "__id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME_TEXT, "
                    + "DESCRIPTION_TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER); ");
            insertDrink(sqLiteDatabase, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertDrink(sqLiteDatabase, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
            insertDrink(sqLiteDatabase, "Filter", "Our best drip coffee", R.drawable.filter);
        };
        if (oldVersion<2){
            sqLiteDatabase.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }

 }
