package com.example.namshopify.viewsupport

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.namshopify.R
import com.example.namshopify.data.Product

class ProductAdapter(private var productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.product_name);
        val imageView: ImageView = view.findViewById(R.id.product_image)
        val quantityView: TextView = view.findViewById(R.id.product_quantity)
        val tagView: TextView = view.findViewById(R.id.product_tag)
        val variantView: TextView = view.findViewById(R.id.product_variant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_view_item, parent, false);
        return ProductHolder(itemView)
    }

    fun setData(newProductList: List<Product>) {
        productList = newProductList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productList.size;
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product: Product = productList[position]
        holder.nameView.text = product.title
        holder.quantityView.text = "Total Quantity:" + product.getInventoryQuantity().toString();
        holder.tagView.text = "Tags:" + product.tags
        holder.variantView.text = "Options:" + product.getVariantText()
        GlideApp.with(holder.imageView)
                .load(product.image.src)
                .centerCrop()
                .into(holder.imageView)
    }

}