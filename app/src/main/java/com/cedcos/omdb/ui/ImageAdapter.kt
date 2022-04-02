package com.cedcos.omdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.cedcos.omdb.R
import com.cedcos.omdb.data.model.ImageModel
import com.cedcos.omdb.databinding.ItemMovieBinding

/**
 * Created by Upendra on 19/2/2022.
 */
class ImageAdapter (private val users: ArrayList<ImageModel>) :
    RecyclerView.Adapter< ImageViewHolder>() {

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
       holder.bind(users.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {

        val binding = inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return ImageViewHolder(binding)
    }
    override fun getItemCount(): Int {
       return users.size
    }

    fun addData(list: List<ImageModel>) {
        users.addAll(list)
    }

}