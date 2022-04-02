package com.cedcos.omdb.network

import com.cedcos.omdb.data.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Upendra on 19/2/2022.
 */
interface ImageService {

    @GET(".")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("apikey") apiKey: String,
        @Query("s") fast: String,
        @Query("type") type: String,
    ): ImageResponse

}