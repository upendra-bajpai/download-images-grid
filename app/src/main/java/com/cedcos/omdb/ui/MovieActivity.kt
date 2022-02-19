package com.cedcos.omdb.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.cedcos.omdb.R
import com.cedcos.omdb.common.FooterAdapter
import com.cedcos.omdb.databinding.ActivityMovieBinding
import com.cedcos.omdb.util.ext.collect
import com.cedcos.omdb.util.ext.collectLast
import com.cedcos.omdb.util.ext.executeWithAction
import com.cedcos.omdb.viewModels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *@collectLast to
 * withLoadStateFooter() returns us a ConcatAdapter, use it to show loading status of the newly added paginatedData
 */
@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private val viewModel: MovieViewModel by viewModels()

    @Inject
    lateinit var userAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setListener()
        setAdapter()
        collectLast(viewModel.userItemsUiStates, ::setMovies)
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
    }

    private fun setListener() {
        binding.btnRetry.setOnClickListener { userAdapter.retry() }
    }


    private fun setAdapter() {
        collect(flow = userAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setMoviesUiState
        )
        binding.rvMovies.adapter = userAdapter.withLoadStateFooter(FooterAdapter(userAdapter::retry))
    }

    private fun setMoviesUiState(loadState: LoadState) {
        binding.executeWithAction {
            moviessUiState = MoviesUiState(loadState)
        }
    }

    private suspend fun setMovies(userItemsPagingData: PagingData<MovieItemUiState>) {
        userAdapter.submitData(userItemsPagingData)
    }

}