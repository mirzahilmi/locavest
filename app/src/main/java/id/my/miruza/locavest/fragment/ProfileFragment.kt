package id.my.miruza.locavest.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import id.my.miruza.locavest.R

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = "email"
        val phoneNumber = "phoneNumber"
        val address = "address"
        val imageUriString = ""
        val username = "username"

        val emailTextView = view.findViewById<TextView>(R.id.email2)
        val phoneNumberTextView = view.findViewById<TextView>(R.id.phonenumber2)
        val addressTextView = view.findViewById<TextView>(R.id.address2)
        val profileImageView = view.findViewById<ImageView>(R.id.profileImageView2)
        val usernameTextView = view.findViewById<TextView>(R.id.textView3)

        emailTextView.text = email
        phoneNumberTextView.text = phoneNumber
        addressTextView.text = address
        usernameTextView.text = username

        if (imageUriString.isNotEmpty()) {
            Glide.with(this).load(imageUriString).into(profileImageView)
        }

        val buttonEdit = view.findViewById<Button>(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            val editProfileFg = EditProfileFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.profileHolderFrameLayout, editProfileFg)
                addToBackStack(null)
                commit()
            }
        }
    }
}
