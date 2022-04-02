package com.cedcos.omdb.data.repository

import com.cedcos.omdb.data.model.ImageModel
import com.cedcos.omdb.network.ImageService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @MoviePagingSource class that will obtain the data, need a Pager that will provide the data here as a flow.
 * @Returns PagingData<Model>
 *
 */

@Singleton
class ImageRepositoryImpl @Inject constructor(
    private val userService: ImageService
) : ImageRepository {

    override suspend fun getImages(page: Int): List<ImageModel> {
                return userService.getMovies(page,"1d094e25","fast","movie").Search
    }
}