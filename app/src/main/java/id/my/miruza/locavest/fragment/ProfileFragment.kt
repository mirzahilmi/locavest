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
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import id.my.miruza.locavest.R
import id.my.miruza.locavest.fragment.SharedViewModel

class ProfileFragment : Fragment() {

    private lateinit var usernameTextView: TextView
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        usernameTextView = view.findViewById(R.id.textView3)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailTextView = view.findViewById<TextView>(R.id.emailShow)
        val phoneNumberTextView = view.findViewById<TextView>(R.id.phonenumberShow)
        val addressTextView = view.findViewById<TextView>(R.id.addressShow)
        val usernameTextView = view.findViewById<TextView>(R.id.textView3)

        sharedViewModel.email.observe(viewLifecycleOwner) { email ->
            emailTextView.text = email
        }

        sharedViewModel.phoneNumber.observe(viewLifecycleOwner) { phoneNumber ->
            phoneNumberTextView.text = phoneNumber
        }

        sharedViewModel.address.observe(viewLifecycleOwner) { address ->
            addressTextView.text = address
        }

        sharedViewModel.username.observe(viewLifecycleOwner) { newUsername ->
            usernameTextView.text = newUsername
        }
        val profileImageView = view.findViewById<ImageView>(R.id.profileImageView2)
        sharedViewModel.imageUri.observe(viewLifecycleOwner) { imageUri ->
            Glide.with(this).load(imageUri).into(profileImageView)
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
        sharedViewModel.username.observe(viewLifecycleOwner) { newUsername ->
            usernameTextView.text = newUsername
        }
    }

    fun updateUsername(newUsername: String) {
        usernameTextView.text = newUsername
    }
}
