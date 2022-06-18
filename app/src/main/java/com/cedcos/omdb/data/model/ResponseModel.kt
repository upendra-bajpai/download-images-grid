package com.cedcos.omdb.data.model


/**
 * Created by Upendra on 19/2/2022.
 */
data class ResponseModel (
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Poster: String,
    val Response:String,
    var description: String = "",
    var amount:Double=0.0
)
{   constructor( descriptions: String, amounts: Double,Title: String) : this(descriptions = descriptions,amounts =amounts ) {
}
     fun string(): String {
        return "$description:$amount"
    }
}
