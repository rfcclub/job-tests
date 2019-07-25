package com.example.namshopify.viewsupport

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.namshopify.R
import com.example.namshopify.data.ProductList
import org.w3c.dom.Text

class TagAdapter(private var productList: ProductList, private val itemClickListener: View.OnClickListener) : RecyclerView.Adapter<TagAdapter.TagHolder>() {

    class TagHolder(val view: View, clickListener: View.OnClickListener) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tag_name);
        init {
            textView.setOnClickListener(clickListener)
        }
    }

    fun setData(newProductList: ProductList) {
        productList = newProductList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tag_view_item, parent, false);
        return TagHolder(itemView, itemClickListener)
    }

    override fun getItemCount(): Int {
        return productList.getAllTags().size;
    }

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        holder.textView.text = productList.getAllTags()[position];
    }

}