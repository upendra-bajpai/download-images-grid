package com.cedcos.omdb.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cedcos.omdb.data.model.ResponseModel
import com.cedcos.omdb.databinding.ItemMovieBinding

/**
 * Created by Upendra on 19/2/2022.
 */
class ImageViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(userItemUiState: ResponseModel) {
        binding.executeWithAction {
            this.userItemUiState = userItemUiState
        }
    }

    fun clearImage(){
        Glide.with(binding.ivPhoto).clear(binding.ivPhoto)
    }

}