package com.example.projekakhirpam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth


class AfterSignUp : AppCompatActivity() {
    lateinit var welcomeTextView : TextView
    lateinit var signoutButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.after_sign_up)
        welcomeTextView = findViewById(R.id.welcome_message)
        signoutButton = findViewById(R.id.btSignout)


        welcomeTextView.text = intent.getStringExtra("WELCOME_MESSAGE")
        Log.d("Oper username", intent.getStringExtra("WELCOME_MESSAGE").toString())

        signoutButton.setOnClickListener{
            signOut()
        }

    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        navigateToSignInPage()
    }


    private fun navigateToSignInPage() {
        val intent = Intent(this, LoginPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }


}