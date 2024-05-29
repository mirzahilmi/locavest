package com.example.locavest

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.locavest.R

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simon4)

        // Get the data passed from MainActivity
        val email = intent.getStringExtra("email")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val address = intent.getStringExtra("address")

        // Find TextViews and set the data
        val emailTextView = findViewById<TextView>(R.id.email2)
        val phoneNumberTextView = findViewById<TextView>(R.id.phonenumber2)
        val addressTextView = findViewById<TextView>(R.id.address2)

        emailTextView.text = email
        phoneNumberTextView.text = phoneNumber
        addressTextView.text = address

        val buttonEdit = findViewById<Button>(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            finish()
        }
    }
}
