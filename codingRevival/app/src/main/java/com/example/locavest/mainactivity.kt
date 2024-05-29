package com.example.locavest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.initialize
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import com.google.firebase.storage.storageMetadata
import com.google.firebase.storage.ktx.storage
import com.google.firebase.ktx.Firebase
import com.google.firebase.FirebaseApp
import com.example.locavest.R
import com.example.locavest.UsernameChangeActivity
import android.app.Application
import android.widget.Button
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    lateinit var customStorage: FirebaseStorage

    private val startForUsernameChange = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val username = result.data?.getStringExtra("username")
            username?.let {
                val usernameTextView = findViewById<TextView>(R.id.textView3) // Adjusted ID to match your layout
                usernameTextView.text = it
            }
        }
    }

    private val startForPasswordReset = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    }

    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            uploadImage(it)
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        customStorage = FirebaseStorage.getInstance("gs://justinniceguy.appspot.com")
        setContentView(R.layout.simon)

        val arrowView = findViewById<View>(R.id.arrow_1)
        val storageRef = customStorage.reference

        arrowView.setOnClickListener {
            val intent = Intent(this, UsernameChangeActivity::class.java)
            startForUsernameChange.launch(intent)
        }

        val forgotPasswordTextView = findViewById<TextView>(R.id.textView8)
        forgotPasswordTextView.setOnClickListener {
            onForgotPasswordClicked()
        }

        val uploadImageView = findViewById<ImageView>(R.id.profileImageView)
        uploadImageView.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }

        val completeButton = findViewById<Button>(R.id.buttonLogin)
        completeButton.setOnClickListener {
            onCompleteButtonClicked()
        }

    }

    private fun onForgotPasswordClicked() {
        val emailEditText = findViewById<EditText>(R.id.email2)
        val email = emailEditText.text.toString()
        val intent = Intent(this, PasswordResetActivity::class.java).apply {
            putExtra("email", email)
        }
        startForPasswordReset.launch(intent)
    }

    private fun onCompleteButtonClicked() {
        val emailEditText = findViewById<EditText>(R.id.email2)
        val phoneNumberEditText = findViewById<EditText>(R.id.phonenumber2)
        val addressEditText = findViewById<EditText>(R.id.address2)

        val email = emailEditText.text.toString()
        val phoneNumber = phoneNumberEditText.text.toString()
        val address = addressEditText.text.toString()

        val intent = Intent(this, ProfileDetailActivity::class.java).apply {
            putExtra("email", email)
            putExtra("phoneNumber", phoneNumber)
            putExtra("address", address)
        }
        startActivity(intent)
    }

    private fun uploadImage(imageUri: Uri) {
        val storageRef = customStorage.reference
        val imagesRef = storageRef.child("images/${imageUri.lastPathSegment}")
        val uploadTask = imagesRef.putFile(imageUri)

        uploadTask.addOnFailureListener {
            Log.e("Firebase", "Image upload failed", it)
        }.addOnSuccessListener { taskSnapshot ->
            imagesRef.downloadUrl.addOnSuccessListener { uri ->
                Log.d("Firebase", "Image upload successful, URL: $uri")
                val uploadImageView = findViewById<ImageView>(R.id.profileImageView)
                Glide.with(this).load(uri).into(uploadImageView)
            }.addOnFailureListener {
                Log.e("Firebase", "Failed to get download URL", it)
            }
        }
    }
}
