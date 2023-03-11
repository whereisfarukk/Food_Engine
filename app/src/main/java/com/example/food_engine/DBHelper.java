package com.example.food_engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.food_engine.Models.MainModel;
import com.example.food_engine.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    static final String DBNAME = "mydatabase.db";
    static final int DBVERSION = 7;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createOrders = "create table orders" +
            "(id integer primary key autoincrement," +
            "name text," +
            "phone text," +
            "price integer," +
            "image integer," +
            "foodName text," +
            "quantity integer," +
            "description text)";

        String createFoods = "create table foods" +
            "(id integer primary key autoincrement," +
            "foodName text," +
            "image integer," +
            "price integer," +
            "description text)";

        String createUsers = "create table users" +
            "(userName text," +
            "phone text primary key," +
            "password text)";

        sqLiteDatabase.execSQL(createOrders);
        sqLiteDatabase.execSQL(createFoods);
        sqLiteDatabase.execSQL(createUsers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        sqLiteDatabase.execSQL("DROP table if exists foods");
        sqLiteDatabase.execSQL("DROP table if exists users");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String name, String phone, int price, int image,  String foodName, String desc, int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodName", foodName);
        values.put("quantity", quantity);
        long id = database.insert("orders", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateOrder(String name, String phone, int quantity, int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("phone", phone);
        values.put("quantity", quantity);
        long row = database.update("orders", values, "id = " + id, null);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public int getCursorIndex(String str) {      // returns the index of the data in the table
        if (str.equals("id")) return 0;
        if (str.equals("name")) return 1;
        if (str.equals("phone")) return 2;
        if (str.equals("price")) return 3;
        if (str.equals("image")) return 4;
        if (str.equals("foodName")) return 5;
        if (str.equals("quantity")) return 6;
        if (str.equals("description")) return 7;
        return -1;
    }

//    public ArrayList<MainModel> getAllFoods() {
//        ArrayList<MainModel> foods = new ArrayList<>();
//        SQLiteDatabase database = getWritableDatabase();
//        Cursor cursor = database.rawQuery("select id, foodName, image, price, description from foods", null);
//        if (cursor.moveToFirst()) {
//            while (cursor.moveToNext()) {
//                MainModel model = new MainModel();
//                model.setId(cursor.getInt(0) + "");
//                model.setName(cursor.getString(1));
//                model.setImage(cursor.getInt(2));
//                model.setPrice(cursor.getInt(3) + "");
//                model.setDescription(cursor.getString(4));
//                foods.add(model);
//            }
//        }
//        return foods;
//    }

    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select id, foodName, image, price, quantity from orders", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) + "");
                model.setOrderQuantity(cursor.getInt(4) + "");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from orders", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (cursor.getInt(0) == id) {
                    return cursor;
                }
            }
        }
        return cursor;
    }

    public int deleteOrder(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id = " + id, null);
    }
}
