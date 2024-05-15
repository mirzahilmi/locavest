package com.example.projekakhirpam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btToLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btToLogin = findViewById(R.id.toLogin)

        btToLogin.setOnClickListener{
            val it = Intent(this, WelcomePage::class.java)
            startActivity(it)
        }
    }
}