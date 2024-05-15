package com.example.projekakhirpam

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LoginPage : AppCompatActivity() {
    lateinit var textSignUp : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        textSignUp = findViewById(R.id.signUpNow)
        val spannableString = SpannableString("Don't have an account? Sign up now")
        val startIndex = spannableString.toString().indexOf("Sign up now")
        val endIndex = startIndex + "Sign up now".length
        val customColor = Color.parseColor("#B5E0FF")

        val colorSpan = ForegroundColorSpan(customColor)
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        textSignUp.text = spannableString
    }

    fun onSignUpClicked(view: View?) {
        val intent = Intent(this, SignupPage::class.java)
        startActivity(intent)
    }
}