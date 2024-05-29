package id.my.miruza.locavest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2

class FirstScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)

        val nextButton = view.findViewById<Button>(R.id.toNext)
        nextButton.setOnClickListener {
            // Get a reference to the ViewPager2 in the WelcomePage activity
            val viewPager2 = requireActivity().findViewById<ViewPager2>(R.id.viewPager2)
            viewPager2.currentItem = 1
        }

        val skipToLogin = view.findViewById<TextView>(R.id.skipWelcome)
        skipToLogin.setOnClickListener{
            val intent = Intent(activity, LoginPage::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return view
    }
}