package com.android.gsixacademy.mymovies.popularMovies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.api.ServiceBuilder
import com.android.gsixacademy.mymovies.api.TheMovieDBApi
import com.android.gsixacademy.mymovies.models.MovieListResponse
import com.android.gsixacademy.mymovies.movie_details.MovieDetailActivity
import kotlinx.android.synthetic.main.movies_list_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.movies_list_fragment, container, false)
        val request = ServiceBuilder.buildService(TheMovieDBApi::class.java)
        val category = arguments?.getString("category")
        val call = request.getMovies(category, 1)
        call.enqueue(object : Callback<MovieListResponse>{
            override fun onResponse(call: Call<MovieListResponse>, response: Response<MovieListResponse>) {
                if (response.isSuccessful){
                    val movieResponse = response.body()
                    val movieList = movieResponse?.results
                    if (movieList != null){
                        var movieListAdapter = MoviesListAdapter(movieList){
                            if (it is MoviesOnClickEvent.onMovieClicked){
                                var intent = Intent (activity, MovieDetailActivity::class.java)
                                intent.putExtra ("MovieId",it.movie.id)
                                startActivity(intent)
                            }
                        }
                        recycler_view_movies.layoutManager = GridLayoutManager (context,2)
                        recycler_view_movies.adapter = movieListAdapter



                    }
                } else {


                }

            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Toast.makeText(activity,"Error",Toast.LENGTH_SHORT).show()
            }
        })





        return view
    }
}