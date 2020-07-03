package com.android.gsixacademy.mymovies.movie_details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.api.ServiceBuilder
import com.android.gsixacademy.mymovies.api.TheMovieDBApi
import com.android.gsixacademy.mymovies.models.MovieCreditsResponse
import com.android.gsixacademy.mymovies.models.MovieResponse
import com.android.gsixacademy.mymovies.models.MovieTrailersResponse
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
                        Picasso.get().load("http://image.tmdb.org/t/p/w185" + movieResponse.poster_path).centerInside().resize(movie_poster_image_view.width,400).into(movie_poster_image_view)
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
                                if (response.isSuccessful) {
                                    val creditsResponse = response.body()
                                    if (creditsResponse != null) {
                                        for (cast in creditsResponse.cast) {
                                            text_view_stars.append(" " + cast.name + ",")
                                        }
                                        for (crew in creditsResponse.crew) {
                                            if (crew.job.equals("Director") || crew.job.equals("Producer")) {
                                                text_view_directors.append(" " + crew.name + ",")
                                            } else if (crew.job.equals("Writer") || crew.job.equals(
                                                    "Author"
                                                )
                                            ) {
                                                text_view_writers.append(" " + crew.name + ",")
                                            }
                                        }
                                        castBtn.setOnClickListener {
                                            val intent = Intent(
                                                this@MovieDetailActivity,
                                                MovieFullCastActivity::class.java
                                            )
                                            intent.putExtra("movieCast", creditsResponse)
                                            startActivity(intent)
                                        }


                                        val call = request.getMovieTrailers(movieId)
                                        call.enqueue(object : Callback<MovieTrailersResponse> {
                                            override fun onFailure(
                                                call: Call<MovieTrailersResponse>,
                                                t: Throwable
                                            ) {
                                                Toast.makeText(
                                                    this@MovieDetailActivity,
                                                    t.message,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }

                                            override fun onResponse(
                                                call: Call<MovieTrailersResponse>,
                                                response: Response<MovieTrailersResponse>
                                            ) {
                                                if (response.isSuccessful) {
                                                    val trailersResponse = response.body()
                                                    if (trailersResponse != null && trailersResponse.results != null) {
                                                        movie_poster_image_view.setOnClickListener {
                                                            val intent = Intent(Intent.ACTION_VIEW)
                                                            intent.data =
                                                                Uri.parse("http ://www.youtube.com/watch?v=" + trailersResponse.results[0].key)
                                                            startActivity(intent)
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
                }
            })
        }
    }

