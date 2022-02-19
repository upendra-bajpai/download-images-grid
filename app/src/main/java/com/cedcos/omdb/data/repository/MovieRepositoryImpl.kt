package com.cedcos.omdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cedcos.omdb.data.model.MovieModel
import com.cedcos.omdb.data.pagingdatasource.MoviePagingDataSource
import com.cedcos.omdb.network.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @MoviePagingSource class that will obtain the data, need a Pager that will provide the data here as a flow.
 * @Returns PagingData<Model>
 *
 */

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val userService: MovieService
) : MovieRepository {
    override fun getMovies(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { MoviePagingDataSource(userService) }
        ).flow
    }


    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}