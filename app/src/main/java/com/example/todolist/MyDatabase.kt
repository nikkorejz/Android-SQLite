//package com.example.todolist
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.util.Log
//
//class MyDatabase(context: Context) :
//    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//    companion object {
//        const val TAG = "MyDatabase"
//        const val DATABASE_NAME = "MyDatabase.db"
//        const val DATABASE_VERSION = 1
//    }
//
//    private val TABLE_DEALS = "deals"
//    private val COL_ID = "id"
//    private val COL_TITLE = "title"
//    private val COL_DONE = "is_done"
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        val createDealsTable = "CREATE TABLE $TABLE_DEALS (" +
//                "$COL_ID INTEGER PRIMARY KEY NOT NULL, " +
//                "$COL_TITLE TEXT NOT NULL, " +
//                "$COL_DONE INTEGER NOT NULL DEFAULT 0" +
//                ")"
//        db?.execSQL(createDealsTable)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        TODO("Not yet implemented")
//    }
//
//    fun add(title: String): Long {
//        val db = writableDatabase
//        val values = ContentValues()
//        values.put(COL_TITLE, title)
//        return db.insert(TABLE_DEALS, null, values)
//    }
//
//    fun getAll(): Collection<MyDeal> {
//        val result = mutableListOf<MyDeal>()
//        val db = readableDatabase
//        val query = "SELECT $COL_ID, $COL_TITLE, $COL_DONE FROM $TABLE_DEALS ORDER BY id DESC"
//        val cursor = db.rawQuery(query, null)
//        if (cursor.moveToFirst()) {
//            do {
//                result.add(
//                    MyDeal(
//                        cursor.getLong(0),
//                        cursor.getString(1),
//                        cursor.getInt(2) > 0
//                    )
//                )
//            } while (cursor.moveToNext())
//        }
//        cursor.close()
//        return result
//    }
//
//    fun delete(id: Long) {
//        val db = writableDatabase
//        val query = "DELETE FROM $TABLE_DEALS WHERE $COL_ID = $id"
//        db.execSQL(query)
//    }
//
//    fun toggle(id: Long) {
//        Log.i(TAG, "Toggle $id")
//        val db = writableDatabase
//        val query = "UPDATE $TABLE_DEALS SET " +
//                "$COL_DONE = ($COL_DONE + 1) % 2 WHERE $COL_ID = $id"
//        db.execSQL(query)
//    }
//}