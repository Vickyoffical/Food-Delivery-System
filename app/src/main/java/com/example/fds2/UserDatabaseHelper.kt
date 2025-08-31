package com.example.fds2.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "UserDB", null, 1) {

    companion object {
        const val TABLE_NAME = "users"
        const val COL_USERNAME = "username"
        const val COL_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = """
            CREATE TABLE $TABLE_NAME (
                $COL_USERNAME TEXT PRIMARY KEY,
                $COL_PASSWORD TEXT
            )
        """.trimIndent()
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(username: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_USERNAME, username)
            put(COL_PASSWORD, password)
        }

        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }

    fun checkUserExists(): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME LIMIT 1", null)
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun validateUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val query = """
            SELECT * FROM $TABLE_NAME 
            WHERE $COL_USERNAME = ? AND $COL_PASSWORD = ?
        """
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }
}
