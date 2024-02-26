package com.example.wally

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "wally"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "favourites"
        const val PHOTO_ID = "id"
        const val PHOTO_URL = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $PHOTO_ID INTEGER PRIMARY KEY,
            $PHOTO_URL TEXT
            )""".trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}