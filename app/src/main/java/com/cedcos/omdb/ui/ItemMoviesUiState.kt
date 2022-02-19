package com.cedcos.omdb.ui

import com.cedcos.omdb.common.BaseUiState
import com.cedcos.omdb.data.model.MovieModel

/**
 * Created by Upendra on 19/2/2022.
 * map it to a required model class with filtering, mapping in viewModel
 */
data class MovieItemUiState(private val userModel: MovieModel) : BaseUiState() {

    fun getImageUrl() = userModel.Poster

    fun getName() = "Title: ${userModel.Title} "

    fun getYear() = "Year: ${ userModel.Year }"

    fun getImdbId() ="Imdb Id: ${ userModel.imdbID }"

}