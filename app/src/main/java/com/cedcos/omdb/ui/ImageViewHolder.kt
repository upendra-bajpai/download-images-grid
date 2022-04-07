package com.cedcos.omdb.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cedcos.omdb.data.model.ImageModel
import com.cedcos.omdb.databinding.ItemMovieBinding
import com.cedcos.omdb.util.ext.executeWithAction

/**
 * Created by Upendra on 19/2/2022.
 */
class ImageViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(userItemUiState: ImageModel) {
        binding.executeWithAction {
            this.userItemUiState = userItemUiState
        }
    }

    fun clearImage(){
        Glide.with(binding.ivPhoto).clear(binding.ivPhoto)
    }

}