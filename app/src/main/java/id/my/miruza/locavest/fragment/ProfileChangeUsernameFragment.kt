package id.my.miruza.locavest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.my.miruza.locavest.R
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import id.my.miruza.locavest.fragment.SharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileChangeUsernameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileChangeUsernameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_change_username, container, false)
        val submitButton = view.findViewById<Button>(R.id.buttonSubmitUsername)
        val usernameEditText = view.findViewById<EditText>(R.id.email2)

        submitButton.setOnClickListener {
            val newUsername = usernameEditText.text.toString()
            sharedViewModel.setUsername(newUsername)
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileChangeUsernameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}