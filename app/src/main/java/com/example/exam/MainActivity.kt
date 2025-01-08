package com.example.exam

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

            val listview = findViewById<ListView>(R.id.list)
            val buyList : EditText = findViewById(R.id.writeitem)
            val button: Button = findViewById(R.id.acceptbutton)
            val db=dbSQlitelist(this,null)
            val todos: MutableList<String> = mutableListOf()
            val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,todos)
            listview.adapter=adapter
        listview.setOnItemClickListener{adapterview,view,i,l->
            val text = listview.getItemAtPosition(i).toString()
            adapter.remove(text)
            db.deleteTask(text)
            Toast.makeText(this,"вы удалили:$text",Toast.LENGTH_LONG).show()
        }
        button.setOnClickListener{
            val text = buyList.text.toString().trim()
            if(text!="")
                adapter.insert(text,0)
            db.newTask(text)
            buyList.text.clear()
        }
        }
    }
