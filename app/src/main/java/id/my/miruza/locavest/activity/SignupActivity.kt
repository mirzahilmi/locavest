package id.my.miruza.locavest.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import id.my.miruza.locavest.R

class SignupActivity : AppCompatActivity() {
    lateinit var btToSignup : Button
    lateinit var textEmail : TextView
    lateinit var textPassword : TextView
    lateinit var SignInButton : Button
    private lateinit var auth: FirebaseAuth

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        val currentUser = auth.currentUser

        btToSignup = findViewById(R.id.buttonToSignup)
        textEmail = findViewById(R.id.LoginEmail)
        textPassword = findViewById(R.id.LoginPassword)
        SignInButton = findViewById(R.id.btLogin)

        SignInButton.setOnClickListener{
            createAccount(textEmail.text.toString(), textPassword.text.toString())
        }

        btToSignup.setOnClickListener{
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
    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }
    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
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

    private fun sendEmailVerification() {
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                Log.w("email-sent", "Email sent successfully")
            }
        // [END send_email_verification]
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
            val intent = Intent(this, MainActivity::class.java)
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
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val welcomeMessage = "Welcome "
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("WELCOME_MESSAGE", welcomeMessage)
            sendEmailVerification()
            startActivity(intent)
            finish() // Optional: finish current activity so the user can't navigate back to it
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
    companion object {
        const val TAG = "EmailPassword"
    }
}