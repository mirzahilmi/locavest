package com.example.locavest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.example.locavest.R

class UsernameChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simon3)

        val submitButton = findViewById<Button>(R.id.buttonSubmitUsername)
        submitButton.setOnClickListener {
            // Get the username from the EditText
            val usernameEditText = findViewById<EditText>(R.id.email2)
            val username = usernameEditText.text.toString()

            // Set the result to be returned to MainActivity
            val resultIntent = Intent().apply {
                putExtra("username", username)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
