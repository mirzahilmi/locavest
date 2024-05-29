package id.my.miruza.locavest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.my.miruza.locavest.R

class ProfileHolderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_holder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileFg = ProfileFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.profileHolderFrameLayout, profileFg)
            addToBackStack(null)
            commit()
        }
    }
}