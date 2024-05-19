package com.example.projekakhirpam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth


class AfterSignUp : AppCompatActivity() {
    lateinit var welcomeTextView : TextView
    lateinit var signoutButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.after_sign_up)
        welcomeTextView = findViewById(R.id.welcome_message)
        signoutButton = findViewById(R.id.btSignout)

        val welcomeMessage = intent.getStringExtra("WELCOME_MESSAGE")
        welcomeTextView.text = welcomeMessage

        signoutButton.setOnClickListener{
            signOut()
        }

    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        navigateToSignInPage()
    }


    private fun navigateToSignInPage() {
        val intent = Intent(this, SignupPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }


}