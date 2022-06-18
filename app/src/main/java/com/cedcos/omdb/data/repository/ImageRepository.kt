package com.cedcos.omdb.data.repository

import com.cedcos.omdb.data.model.ResponseModel

/**
 * Created by Upendra on 19/2/2022.
 */

interface ImageRepository {
    suspend fun  getImages(page: Int): List<ResponseModel>
}