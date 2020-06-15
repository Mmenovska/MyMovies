package com.android.gsixacademy.mymovies.popularMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.api.ServiceBuilder
import com.android.gsixacademy.mymovies.api.TheMovieDBApi
import com.android.gsixacademy.mymovies.models.PopularMovies
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
        call.enqueue(object : Callback<PopularMovies>{
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful){
                    val movieResponse = response.body()
                    val movieList = movieResponse?.results
                    if (movieList != null){

                    }
                } else {


                }

            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(activity,"Error",Toast.LENGTH_SHORT).show()
            }
        })





        return view
    }
}