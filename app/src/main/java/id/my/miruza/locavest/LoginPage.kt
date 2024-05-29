package id.my.miruza.locavest

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import id.my.miruza.locavest.activity.MainActivity


class LoginPage : AppCompatActivity() {
    lateinit var textSignUp : TextView
    lateinit var loginEmail : TextView
    lateinit var loginPassword : TextView
    lateinit var btLogin : TextView
    lateinit var googleLogin : Button
    private lateinit var auth: FirebaseAuth

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        textSignUp = findViewById(R.id.signUpNow)
        loginEmail = findViewById(R.id.LoginEmail)
        loginPassword = findViewById(R.id.LoginPassword)
        btLogin = findViewById(R.id.btLogin)
        googleLogin = findViewById(R.id.btToLogin)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        val currentUser = auth.currentUser

        val spannableString = SpannableString("Don't have an account? Sign up now")
        val startIndex = spannableString.toString().indexOf("Sign up now")
        val endIndex = startIndex + "Sign up now".length
        val customColor = Color.parseColor("#B5E0FF")

        val colorSpan = ForegroundColorSpan(customColor)
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        textSignUp.text = spannableString

        btLogin.setOnClickListener{
            signIn(loginEmail.text.toString(), loginPassword.text.toString())
        }

        googleLogin.setOnClickListener {
            startSignIn()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
//            reload()
        }
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(SignupPage.TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(SignupPage.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val welcomeMessage = "Welcome " + loginEmail.text
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("WELCOME_MESSAGE", welcomeMessage)
            startActivity(intent)
            finish() // Optional: finish current activity so the user can't navigate back to it
        } else {

        }
    }

    private fun reload() {
        auth.currentUser?.reload()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "reload:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Failed to reload user.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
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

    private fun handleSignInFailure(error: Exception?) {
        // Show a message to the user if Sign in failed
        println("Sign-in failed: ${error?.localizedMessage}")
    }

    private fun handleSignInSuccess(user: FirebaseUser?) {
        user?.let {
            val welcomeMessage = "Welcome " + loginEmail.text
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("WELCOME", "AAAAAAAaaaaaaaaaaa")
            Toast.makeText(this, "LOgin berhasil", Toast.LENGTH_SHORT).show()
            startActivity(intent)

        } ?:{
            println("Sign-in failed: user is null")
        }
    }
    fun onSignUpClicked(view: View?) {
        val intent = Intent(this, SignupPage::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}