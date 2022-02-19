package com.cedcos.omdb.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.cedcos.omdb.data.repository.MovieRepository
import com.cedcos.omdb.ui.MovieItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Upendra on 19/2/2022.
 * map it to a required model class
 * cachedIn() from Coroutine caches data
 */
@HiltViewModel
class MovieViewModel @Inject constructor(userRepository: MovieRepository) : ViewModel() {
    val userItemsUiStates = userRepository.getMovies()
        .map { pagingData ->
            pagingData.map { userModel -> MovieItemUiState(userModel) }
        }.cachedIn(viewModelScope)
}
