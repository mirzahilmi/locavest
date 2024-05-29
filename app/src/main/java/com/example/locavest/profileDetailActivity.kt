package com.example.locavest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_profile)

        // Get the data passed from MainActivity
        val email = intent.getStringExtra("email")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val address = intent.getStringExtra("address")
        val imageUriString = intent.getStringExtra("imageUri")
        val username = intent.getStringExtra("username")

        // Find TextViews and set the data
        val emailTextView = findViewById<TextView>(R.id.email2)
        val phoneNumberTextView = findViewById<TextView>(R.id.phonenumber2)
        val addressTextView = findViewById<TextView>(R.id.address2)
        val profileImageView = findViewById<ImageView>(R.id.profileImageView2)
        val usernameTextView = findViewById<TextView>(R.id.textView3)

        emailTextView.text = email
        phoneNumberTextView.text = phoneNumber
        addressTextView.text = address
        usernameTextView.text = username

        if (!imageUriString.isNullOrEmpty()) {
            Glide.with(this).load(imageUriString).into(profileImageView)
        }

        val buttonEdit = findViewById<Button>(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("phoneNumber", phoneNumber)
            intent.putExtra("address", address)
            intent.putExtra("imageUri", imageUriString)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }
}
