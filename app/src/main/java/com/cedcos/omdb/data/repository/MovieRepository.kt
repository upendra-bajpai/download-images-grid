package com.cedcos.omdb.data.repository

import androidx.paging.PagingData
import com.cedcos.omdb.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Upendra on 19/2/2022.
 */

interface MovieRepository {
    fun getMovies(): Flow<PagingData<MovieModel>>
}