package com.android.gsixacademy.mymovies.api

import com.android.gsixacademy.mymovies.models.PopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val apiKey = "api_key=e15e7925e8d5d55f874a5c804565ba5a"

interface TheMovieDBApi {

//    @GET ("/3/movie/popular")
//    fun getPopularMovie (@Query ("api_key") key : String ) : Call<PopularMovies>

    @GET ("/3/movie/{category}?" + apiKey)
    fun getMovies (@Path("category") category: String?, @Query ("page") page : Int?) : Call<PopularMovies>
}