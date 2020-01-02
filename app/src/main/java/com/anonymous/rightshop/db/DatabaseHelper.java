package com.anonymous.rightshop.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.anonymous.rightshop.model.CartItem;
import com.anonymous.rightshop.model.CategoryItems;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyOrders.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "ORDERS";
    // Column names
    private static final String COLUMN_ID = BaseColumns._ID;
    private static final String COLUMN_ITEM_NAME = "item_name";
    private static final String COLUMN_ITEM_SERIAL = "item_serial";
    private static final String COLUMN_ITEM_PRICE = "price";
    private static final String COLUMN_ITEM_IMAGE = "item_image_url";
    private static final String COLUMN_DATE_TIME = "datetime";
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ITEM_NAME + " text ," +
            COLUMN_ITEM_SERIAL + " text ," +
            COLUMN_ITEM_PRICE + " integer ," +
            COLUMN_ITEM_IMAGE + " text ," +
            COLUMN_DATE_TIME + " text" +
            ");";
    private static final String TAG = "ERROR";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            //Log.d(TAG,"Successfully Created");
        } catch (SQLException e) {
            Toast.makeText(null, "There is Problem in Creating Table!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + "");
            onCreate(db);
        } catch (SQLException e) {
            Toast.makeText(null, e.getMessage() + "", Toast.LENGTH_SHORT).show();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void insert(Context context, CartItem addToCart) {
        try ( SQLiteDatabase db = getWritableDatabase()) {
            String currentTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_ITEM_NAME, addToCart.getName());
            cv.put(COLUMN_ITEM_SERIAL,addToCart.getSerialNumber());
            cv.put(COLUMN_ITEM_PRICE, addToCart.getOldPrice());
            cv.put(COLUMN_DATE_TIME, currentTime);
            cv.put(COLUMN_ITEM_IMAGE,addToCart.getImage());
            db.insert(TABLE_NAME, null, cv);
            //else
            //Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Log.d(TAG, "insert: SQLException : " + e.getMessage());
        }
    }

    public ArrayList<CartItem> getOrder() {
        ArrayList<CartItem> arrayList = new ArrayList<>();

        //First Make an object of SQLiteDatabase and make Database Readable because we will be getting data
        SQLiteDatabase db = getReadableDatabase();
        //In this step we Define Columns we want to patch
        String[] columns = {COLUMN_ID, COLUMN_ITEM_NAME,COLUMN_ITEM_SERIAL, COLUMN_ITEM_PRICE,COLUMN_ITEM_IMAGE};
        //Cursor is Default Class of java(Android) We use it to get and insert data in the database
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
            //Here in if condition it checks whether there is any data or not, if yes then do while loop will be run!
            if (cursor.moveToFirst()) {
                //this will run until last data!!..
                do {
                    //Here it is getting the data of rows!! Each Time it will get Each rows Values!!
                    long id = Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                    String itemHeading = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME));
                    String itemSerial =cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_SERIAL));
                    String itemPrice = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PRICE));
                    String itemImage = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_IMAGE));
                    CartItem obj = new CartItem(id,itemHeading,itemPrice, itemSerial,itemImage);
                    arrayList.add(obj);
                } while (cursor.moveToNext());

            }
        } catch (SQLException e) {
            Toast.makeText(null, "There is Problem in getting the data", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return arrayList;
    }

    @SuppressLint("SetTextI18n")
    public long delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {String.valueOf(id)};
        long row = db.delete(TABLE_NAME, COLUMN_ID + " = ?", args);
        db.close();
       //OrdersFragment.Total.setText(addAllValues()+"");
        return row;
    }


    public int addAllValues(){
        SQLiteDatabase db = getWritableDatabase();
        int total = 0;

        @SuppressLint("Recycle") Cursor c = db.rawQuery("SELECT SUM(" + (COLUMN_ITEM_PRICE) + ") FROM " + TABLE_NAME, null);
        if(c.moveToFirst()){
            total = c.getInt(0);
        }
        db.close();
        return total;
    }

    public int cartItems(){
        int total = 0;
        SQLiteDatabase db = getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("SELECT COUNT(" + ("*") + ") FROM " + TABLE_NAME, null);
        if(c.moveToFirst()){
            total = c.getInt(0);
        }
        db.close();
        return total;
    }

}
