package com.example.projekakhirpam
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig
import com.firebase.ui.auth.AuthUI.IdpConfig.EmailBuilder
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupPage : AppCompatActivity() {
    lateinit var btToSignup : Button

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)
        FirebaseApp.initializeApp(this)
        btToSignup = findViewById(R.id.buttonToSignup)

        btToSignup.setOnClickListener{
            startSignIn()
        }
    }
    private fun startSignIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // If success then call the Success method
            val user = FirebaseAuth.getInstance().currentUser
            handleSignInSuccess(user)
        } else {
            handleSignInFailure(response?.error)
        }
    }

    private fun handleSignInSuccess(user: FirebaseUser?) {
        user?.let {
            val welcomeMessage = "Welcome ${it.displayName}"
            val intent = Intent(this, AfterSignUp::class.java)
            intent.putExtra("WELCOME_MESSAGE", welcomeMessage)
            startActivity(intent)
            finish()
        } ?:{
            println("Sign-in failed: user is null")
        }
    }

    private fun handleSignInFailure(error: Exception?) {
        // Show a message to the user if Sign in failed
        println("Sign-in failed: ${error?.localizedMessage}")
    }
}