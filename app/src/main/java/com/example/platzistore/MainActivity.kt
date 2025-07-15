package com.example.platzistore

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orderverify.databinding.ActivityMainBinding
import com.example.platzistore.adapter.ProductAdapter
import com.example.platzistore.api.RetrofitInstance
import com.example.platzistore.model.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchProducts()
    }

    private fun fetchProducts() {
        RetrofitInstance.api.getProducts().enqueue(object : Callback<List<Products>> {
            override fun onResponse(
                call: Call<List<Products>>,
                response: Response<List<Products>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val adapter = ProductAdapter(response.body()!!)
                    binding.recyclerViewTshirts.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.recyclerViewTshirts.adapter = adapter
                } else {
                    Log.e("API", "Response error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                Log.e("API", "Network error: ${t.message}")
            }
        })
    }
}
