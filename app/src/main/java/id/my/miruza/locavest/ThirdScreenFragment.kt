package id.my.miruza.locavest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ThirdScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        val loginButton = view.findViewById<Button>(R.id.toNext)
        loginButton.setOnClickListener {
            // Get a reference to the ViewPager2 in the WelcomePage activity
            val intent = Intent(activity, LoginPage::class.java)
            startActivity(intent)

            activity?.finish()
        }
        return view
    }
}