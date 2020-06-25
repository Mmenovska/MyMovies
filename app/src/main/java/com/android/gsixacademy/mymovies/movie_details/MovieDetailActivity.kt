package com.android.gsixacademy.mymovies.movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.api.ServiceBuilder
import com.android.gsixacademy.mymovies.api.TheMovieDBApi
import com.android.gsixacademy.mymovies.models.MovieCreditsResponse
import com.android.gsixacademy.mymovies.models.MovieResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getIntExtra("MovieId",0)
        val request = ServiceBuilder.buildService(TheMovieDBApi::class.java)
        val call = request.getMovieDetails(movieId)
        call.enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MovieDetailActivity,t.message,Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    val movieResponse = response.body()
                    if (movieResponse != null){
                        Picasso.get().load("http://image.tmdb.org/t/p/w185" + movieResponse.poster_path).fit().into(movie_poster_image_view)
                        text_view_name.setText(movieResponse.original_title)
                        text_view_overview.append("\n" + movieResponse.overview)
                        val call = request.getMovieCredits(movieId)
                        call.enqueue(object : Callback<MovieCreditsResponse>{
                            override fun onFailure(call: Call<MovieCreditsResponse>, t: Throwable) {
                                Toast.makeText(this@MovieDetailActivity,t.message,Toast.LENGTH_SHORT).show()
                            }
                            override fun onResponse(
                                call: Call<MovieCreditsResponse>,
                                response: Response<MovieCreditsResponse>) {
                                if (response.isSuccessful){
                                    val creditsResponse = response.body()
                                    if (creditsResponse != null){
                                        for (cast in creditsResponse.cast){
                                            text_view_stars.append(" " + cast.name + ",")
                                        }
                                        for (crew in creditsResponse.crew){
                                            if (crew.job.equals("Director") || crew.job.equals("Producer")){
                                                text_view_directors.append(" " + crew.name + ",")
                                            }else if (crew.job.equals("Writer") || crew.job.equals("Author")){
                                                text_view_writers.append(" " + crew.name + ",")
                                            }
                                        }
                                    }
                                }
                            }
                        })
                    }
                }
            }
        })
    }
}
