package com.cedcos.omdb.ui

import androidx.recyclerview.widget.RecyclerView
import com.cedcos.omdb.databinding.ItemMovieBinding
import com.cedcos.omdb.util.ext.executeWithAction

/**
 * Created by Upendra on 19/2/2022.
 */
class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(userItemUiState: MovieItemUiState) {
        binding.executeWithAction {
            this.userItemUiState = userItemUiState
        }
    }
}