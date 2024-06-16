package id.my.miruza.locavest.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.my.miruza.locavest.data.CartItem
import id.my.miruza.locavest.adapter.CartItemsAdapter
import id.my.miruza.locavest.RetrofitInstance
import id.my.miruza.locavest.activity.CheckoutActivity
import id.my.miruza.locavest.databinding.FragmentCartBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private var cartItems: List<CartItem> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemsRecyclerView.layoutManager = LinearLayoutManager(context)
        fetchCartItems()
        binding.btnCheckout.setOnClickListener {
            val intent = Intent(requireContext(), CheckoutActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelableArrayList("cartItems", ArrayList(cartItems))
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchCartItems() = binding.itemsRecyclerView.apply {
        RetrofitInstance.api.getCartItems().enqueue(object : Callback<List<CartItem>> {
            override fun onResponse(call: Call<List<CartItem>>, response: Response<List<CartItem>>) {
                if (response.isSuccessful) {
                    cartItems = response.body() ?: emptyList()
                    binding.tvEmpty.visibility =  if (cartItems.isEmpty()) View.VISIBLE else View.GONE
                    adapter = CartItemsAdapter(cartItems.toMutableList())
                    binding.itemsRecyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<CartItem>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}