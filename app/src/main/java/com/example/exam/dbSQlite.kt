package com.example.exam

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbSQlite(val context: Context,val factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context,"app",factory,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY,login TEXT,email TEXT,pass TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }
    fun adduser(user:user){
        val values = ContentValues()
        values.put("login",user.login)
        values.put("email",user.email)
        values.put("pass",user.pass)
        val db = this.writableDatabase
        db.insert("users",null,values)
        db.close()
    }
    fun GetUser(login:String,pass:String):Boolean{
        val db = this.readableDatabase

        val resoult = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND pass = '$pass'",null)
        return resoult.moveToFirst()
    }
}