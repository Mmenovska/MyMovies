package com.android.gsixacademy.mymovies.api

import com.android.gsixacademy.mymovies.models.MovieCreditsResponse
import com.android.gsixacademy.mymovies.models.MovieListResponse
import com.android.gsixacademy.mymovies.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val apiKey = "api_key=e15e7925e8d5d55f874a5c804565ba5a"

interface TheMovieDBApi {

//    @GET ("/3/movie/popular")
//    fun getPopularMovie (@Query ("api_key") key : String ) : Call<PopularMovies>

    @GET ("movie/{category}?" + apiKey)
    fun getMovies (@Path("category") category: String?, @Query ("page") page : Int?) : Call<MovieListResponse>

    @GET ("movie/{movieID}?" + apiKey)
    fun getMovieDetails (@Path ("movieID") movieId : Int?): Call <MovieResponse>

    @GET("movie/{movieID}/credits?" + apiKey)
    fun getMovieCredits (@Path("movieID") movieId : Int?) : Call <MovieCreditsResponse>
}