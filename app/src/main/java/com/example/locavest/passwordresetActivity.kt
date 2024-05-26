package com.example.locavest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.locavest.R

class PasswordResetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simon2)

        val email = intent.getStringExtra("email")
        val emailEditText = findViewById<EditText>(R.id.email2)
        emailEditText.setText(email)

        val submitButton = findViewById<Button>(R.id.buttonLogin)
        submitButton.setOnClickListener {
            // Navigate back to MainActivity
            val resultIntent = Intent()
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
