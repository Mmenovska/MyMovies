package com.android.gsixacademy.mymovies.people

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.api.ServiceBuilder
import com.android.gsixacademy.mymovies.api.TheMovieDBApi
import com.android.gsixacademy.mymovies.models.PeopleResponse
import com.android.gsixacademy.mymovies.movie_details.adapters.CastOnClickEvent
import com.android.gsixacademy.mymovies.people.adapters.PeopleAdapter
import kotlinx.android.synthetic.main.movies_list_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleFragment : Fragment() {

    companion object {
        fun newInstance() : PeopleFragment = PeopleFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.movies_list_fragment,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ServiceBuilder.buildService(TheMovieDBApi :: class.java)
        val call = request.getPopularPersons()

        call.enqueue(object : Callback<PeopleResponse>{
            override fun onFailure(call: Call<PeopleResponse>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<PeopleResponse>,
                response: Response<PeopleResponse>
            ) {
                if (response.isSuccessful){
                    val peopleList = response.body()?.results
                    if (peopleList != null){
                        val peopleAdapter = PeopleAdapter (peopleList) {
                            if (it is CastOnClickEvent.onCastClickEvent){
                                val intent = Intent (activity,PeopleDetailActivity::class.java)
                                intent.putExtra("castId", it.cast.id)
                                startActivity(intent)
                            }
                        }

                        recycler_view_movies.layoutManager = LinearLayoutManager(activity)
                        recycler_view_movies.adapter = peopleAdapter
                    }
                }

            }
        })




    }
}