package com.example.fds2.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.fds2.Restaurant

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "FoodApp.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE users (" +
                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT UNIQUE, " +
                    "password TEXT)"
        )

        db?.execSQL(
            "CREATE TABLE menu(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "price INTEGER)"
        )

        db?.execSQL(
            "CREATE TABLE cart(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "itemName TEXT, " +
                    "price INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS users")
        db?.execSQL("DROP TABLE IF EXISTS menu")
        db?.execSQL("DROP TABLE IF EXISTS cart")
        onCreate(db)
    }

    fun insertUser(username: String, password: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        return db.insert("users", null, values)
    }

    fun checkUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM users WHERE username=? AND password=?",
            arrayOf(username, password)
        )
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun insertRestaurant(name: String, category: String, rating: Double) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("category", category)
        values.put("rating", rating)
        db.insert("restaurants", null, values)
    }

    fun getAllRestaurants(): List<Restaurant> {
        val resList = mutableListOf<Restaurant>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM restaurants", null)
        if (cursor.moveToFirst()) {
            do {
                val res = Restaurant(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    category = cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    rating = cursor.getDouble(cursor.getColumnIndexOrThrow("rating"))
                )
                resList.add(res)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return resList
    }


}
