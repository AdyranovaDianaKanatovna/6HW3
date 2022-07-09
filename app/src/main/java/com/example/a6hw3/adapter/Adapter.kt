package com.example.a6hw3.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.a6hw3.MainActivity
import com.example.a6hw3.R
import com.example.a6hw3.databinding.ItemActivityBinding

class Adapter(private var listener: MainActivity) :
    RecyclerView.Adapter<Adapter.AdapterHolder>() {
    private val imageList = arrayListOf<Uri>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
        return AdapterHolder(view)

    }

    class AdapterHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemActivityBinding.bind(item)
        fun bind(mainImage: Uri, listener: MainActivity) = with(binding) {
            image.setImageURI(mainImage)
            imageShadow.visibility = INVISIBLE
            itemView.setOnClickListener {
                if (!imageShadow.isVisible) {
                    listener.onClick(mainImage)
                    imageShadow.visibility = VISIBLE
                } else {
                    listener.deleteClick(mainImage)
                    imageShadow.visibility = INVISIBLE
                }
            }
        }
    }

    interface Listener {
        fun onClick(mainImage: Uri)
        fun deleteClick(mainImage: Uri)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addImage(image: Uri) {
        this.imageList.addAll(listOf(image))
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.bind(imageList[position],listener)
    }

    override fun getItemCount() = imageList.size
}
