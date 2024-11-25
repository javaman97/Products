package com.younglee.products.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aman.products.R
import com.younglee.products.model.ProductX


class ProductPagingAdapter : PagingDataAdapter<ProductX, ProductPagingAdapter.ProductViewHolder>(
    ProductDiffCallback()
) {
    inner class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val title:TextView = itemView.findViewById(R.id.txtTitle)
        val price:TextView = itemView.findViewById(R.id.txtPrice)
        val desc:TextView = itemView.findViewById(R.id.txtDesc)
        val rating:TextView = itemView.findViewById(R.id.txtRating)
        val prodImage:ImageView = itemView.findViewById(R.id.imgThumbnail)
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<ProductX>() {

        override fun areItemsTheSame(oldItem: ProductX, newItem: ProductX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductX, newItem: ProductX): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

        product?.let {
            holder.title.text = product.title
            holder.price.text = product.price.toString()
            holder.desc.text = product.description
            holder.rating.text = product.rating.toString()

            holder.prodImage.load(product.image) {
                crossfade(true)
                placeholder(R.drawable.placeholder_image)
                error(R.drawable.error_image)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)

        return  ProductViewHolder(view)
    }
}