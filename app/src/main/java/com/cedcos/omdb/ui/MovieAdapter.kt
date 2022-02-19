package com.cedcos.omdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.cedcos.omdb.R
import com.cedcos.omdb.databinding.ItemMovieBinding
import javax.inject.Inject

/**
 * Created by Upendra on 19/2/2022.
 */
class MovieAdapter @Inject constructor() :
    PagingDataAdapter<MovieItemUiState, MovieViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { userItemUiState -> holder.bind(userItemUiState) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val binding = inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    object Comparator : DiffUtil.ItemCallback<MovieItemUiState>() {
        override fun areItemsTheSame(oldItem: MovieItemUiState, newItem: MovieItemUiState): Boolean {
            return oldItem.getImdbId() == newItem.getImdbId()
        }

        override fun areContentsTheSame(
            oldItem: MovieItemUiState,
            newItem: MovieItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

}