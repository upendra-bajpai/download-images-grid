package com.cedcos.omdb.common

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.cedcos.omdb.databinding.ItemPagingFooterBinding
import com.cedcos.omdb.util.ext.executeWithAction

/**
 * Created by Upendra on 19/2/2022.
 */
class FooterViewHolder(
    private val binding: ItemPagingFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.executeWithAction {
            footerUiState = FooterUiState(loadState)
        }
    }
}

