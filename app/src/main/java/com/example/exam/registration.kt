package com.example.exam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        val userLogin: EditText = findViewById(R.id.userLogin)
        val userEmail: EditText = findViewById(R.id.usereMail)
        val userPass: EditText = findViewById(R.id.userPassword)
        val button: Button = findViewById(R.id.RegButton)
        val linkToAuth: TextView = findViewById(R.id.reglink)
        linkToAuth.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        button.setOnClickListener{
            val login=userLogin.text.toString().trim()
            val pass=userPass.text.toString().trim()
            val email=userEmail.text.toString().trim()
            if(login==""||email==""|| pass=="")
                Toast.makeText(this,"не все поля заполнены",Toast.LENGTH_LONG).show()
            else{
                val user= user(login,email,pass)

                val db = dbSQlite(this,null)
                db.adduser(user)
                Toast.makeText(this,"пользователь $login добавлен",Toast.LENGTH_LONG).show()
                userLogin.text.clear()
                userPass.text.clear()
                userEmail.text.clear()
            }

        }
    }
}