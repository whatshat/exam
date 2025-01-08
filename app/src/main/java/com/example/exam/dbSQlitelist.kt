package com.example.exam

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class dbSQlitelist(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 3) { // Увеличил версию базы данных

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE MarketList (id INTEGER PRIMARY KEY AUTOINCREMENT, task TEXT)"
        db?.execSQL(query)
        Log.d("dbSQlitelist", "Database created with table MarketList")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS MarketList")
        onCreate(db)
        Log.d("dbSQlitelist", "Database upgraded with table MarketList")
    }

    fun newTask(task: String) {
        val values = ContentValues()
        values.put("task", task)
        val db = this.writableDatabase
        db.insert("MarketList", null, values)
        db.close()
        Log.d("dbSQlitelist", "New task inserted: $task")
    }

    fun getTasks(): MutableList<String> {
        val list: MutableList<String> = mutableListOf()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM MarketList", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndexOrThrow("task")))
            } while (cursor.moveToNext())
        } else {
            Log.d("dbSQlitelist", "No tasks found in database")
        }
        cursor.close()
        db.close()
        Log.d("dbSQlitelist", "Tasks retrieved: ${list.size}")
        return list
    }

    fun deleteTask(task: String) {
        val db = this.writableDatabase
        db.delete("MarketList", "task = ?", arrayOf(task))
        db.close()
        Log.d("dbSQlitelist", "Task deleted: $task")
    }
}
