package com.android.gsixacademy.mymovies.models

class MovieListResponse (
    val page : Int?,
    val total_pages : Int?,
    val total_results : Int?,
    val results : ArrayList <MovieResult>
) {

}
