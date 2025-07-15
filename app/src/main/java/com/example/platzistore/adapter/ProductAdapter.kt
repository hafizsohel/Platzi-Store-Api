package com.example.platzistore.adapter


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orderverify.databinding.ItemProductsBinding
import com.example.platzistore.model.Products

class ProductAdapter(private val products: List<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Products) {
            binding.textTitle.text = product.title
            binding.textPrice.text = "$${product.price}"

            val firstImageUrl = product.images.firstOrNull()

            if (firstImageUrl.isNullOrEmpty()) {
                binding.imageProduct.setImageResource(android.R.drawable.ic_menu_report_image)
            } else {
                Glide.with(binding.imageProduct.context)
                    .load(firstImageUrl)
                    .placeholder(android.R.color.darker_gray)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(binding.imageProduct)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}
