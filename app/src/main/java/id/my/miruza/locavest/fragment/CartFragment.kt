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
import id.my.miruza.locavest.activity.MainActivity
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
        // TODO: Asynchronous call
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
            override fun onResponse(
                call: Call<List<CartItem>>, response: Response<List<CartItem>>
            ) {
                cartItems = response.body() ?: emptyList()
                cartItems = listOf(
                    CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/8/89/Tomato_je.jpg",
                        "Tomatoes",
                        25_000F,
                        "Kg",
                        15
                    ), CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg",
                        "Apples",
                        30_000F,
                        "Kg",
                        10
                    ), CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg",
                        "Bananas",
                        18_000F,
                        "Kg",
                        25
                    ), CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/a/aa/Carrot.jpg",
                        "Carrots",
                        22_000F,
                        "Kg",
                        12
                    ), CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/e/ed/Broccoli_and_cross_section_edit.jpg",
                        "Broccoli",
                        28_000F,
                        "Kg",
                        8
                    ), CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/0/0f/PPotatoes.jpg",
                        "Potatoes",
                        16_000F,
                        "Kg",
                        30
                    ), CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/2/2f/Cucumber-1.jpg",
                        "Cucumbers",
                        20_000F,
                        "Kg",
                        18
                    ), CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/2/25/Red_and_yellow_bell_peppers.jpg",
                        "Bell Peppers",
                        35_000F,
                        "Kg",
                        7
                    ), CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/4/47/Spinach_leaves.jpg",
                        "Spinach",
                        26_000F,
                        "Kg",
                        9
                    ), CartItem(
                        "https://upload.wikimedia.org/wikipedia/commons/a/a1/Oyster_mushroom.JPG",
                        "Mushrooms",
                        40_000F,
                        "Kg",
                        5
                    )
                )
                // TODO: Proper exit upon API call failure
                binding.itemsRecyclerView.adapter = CartItemsAdapter(cartItems)
            }

            override fun onFailure(call: Call<List<CartItem>>, t: Throwable) {
                call.cancel()
            }
        })
    }
}