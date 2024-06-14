package id.my.miruza.locavest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.FirebaseApp
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import androidx.fragment.app.activityViewModels
import id.my.miruza.locavest.R
import id.my.miruza.locavest.fragment.SharedViewModel

class EditProfileFragment : Fragment() {

    private lateinit var customStorage: FirebaseStorage
    private var uploadedImageUri: Uri? = null
    private lateinit var usernameTextView: TextView
    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val startForPasswordReset = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    }

    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            uploadImage(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FirebaseApp.initializeApp(requireContext())
        customStorage = FirebaseStorage.getInstance("gs://fir-developer-arena.appspot.com")
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameTextView = view.findViewById(R.id.textView3)

        val emailEditText = view.findViewById<EditText>(R.id.emailEdit)
        val phoneNumberEditText = view.findViewById<EditText>(R.id.phonenumberEdit)
        val addressEditText = view.findViewById<EditText>(R.id.addressEdit)


        sharedViewModel.username.observe(viewLifecycleOwner) { newUsername ->
            usernameTextView.text = newUsername
        }

        val arrowView = view.findViewById<View>(R.id.arrow_1)
        val storageRef = customStorage.reference

        arrowView.setOnClickListener {
            val profileChangeUsernameFg = ProfileChangeUsernameFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.profileHolderFrameLayout, profileChangeUsernameFg)
                addToBackStack(null)
                commit()
            }
        }

        val forgotPasswordTextView = view.findViewById<TextView>(R.id.textView8)
        forgotPasswordTextView.setOnClickListener {
            val resetPasswordFg = ResetPasswordFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.profileHolderFrameLayout, resetPasswordFg)
                addToBackStack(null)
                commit()
            }
        }

        val uploadImageView = view.findViewById<ImageView>(R.id.profileImageView)
        uploadImageView.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }

        val completeButton = view.findViewById<Button>(R.id.buttonLogin)
        completeButton.setOnClickListener {
            sharedViewModel.setEmail(emailEditText.text.toString())
            sharedViewModel.setPhoneNumber(phoneNumberEditText.text.toString())
            sharedViewModel.setAddress(addressEditText.text.toString())
            requireActivity().supportFragmentManager.popBackStack()
        }
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
                val uploadImageView = view?.findViewById<ImageView>(R.id.profileImageView)
                Glide.with(this).load(uri).into(uploadImageView!!)
                uploadedImageUri = uri
                sharedViewModel.setImageUri(uri.toString())
            }.addOnFailureListener {
                Log.e("Firebase", "Failed to get download URL", it)
            }
        }
    }
    fun updateUsername(newUsername: String) {
        usernameTextView.text = newUsername
    }
}