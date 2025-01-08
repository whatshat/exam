package com.example.exam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val userLogin: EditText = findViewById(R.id.userLoginAuth)
        val userPass: EditText = findViewById(R.id.userPasswordAuth)
        val button: Button = findViewById(R.id.RegButtonAuth)
        val linkToReg: TextView = findViewById(R.id.inlink)

        linkToReg.setOnClickListener {
            val intent = Intent(this, registration::class.java)
            startActivity(intent)
        }
        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()
            if (login == "" || pass == "")
                Toast.makeText(this, "не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val db = dbSQlite(this, null)
                val auth = db.GetUser(login, pass)
                if (auth) {
                    Toast.makeText(this, "пользователь $login авторизирован", Toast.LENGTH_LONG)
                        .show()
                    userLogin.text.clear()
                    userPass.text.clear()

                    val list = Intent(this,MainActivity::class.java)
                    startActivity(list)

                } else
                    Toast.makeText(this, "пользователь $login НЕ авторизирован", Toast.LENGTH_LONG)
                        .show()

            }
        }
    }
}
