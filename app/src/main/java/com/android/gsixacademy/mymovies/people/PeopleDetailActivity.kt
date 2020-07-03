package com.android.gsixacademy.mymovies.people

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.api.ServiceBuilder
import com.android.gsixacademy.mymovies.api.TheMovieDBApi
import com.android.gsixacademy.mymovies.models.PersonModel
import com.android.gsixacademy.mymovies.models.PersonMovieResponse
import com.android.gsixacademy.mymovies.movie_details.MovieDetailActivity
import com.android.gsixacademy.mymovies.people.adapters.PersonMoviesAdapter
import com.android.gsixacademy.mymovies.people.adapters.PersonMoviesClickEvent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_people_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PeopleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_detail)


        val peopleId = intent.getIntExtra("peopleId", 0)
        val request = ServiceBuilder.buildService(TheMovieDBApi::class.java)
        val call = request.getPerson(peopleId)

        call.enqueue(object : Callback<PersonModel> {
            override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                Toast.makeText(this@PeopleDetailActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PersonModel>, response: Response<PersonModel>) {
                if (response.isSuccessful) {
                    val peopleResponse = response.body()
                    if (peopleResponse != null) {
                        Picasso.get()
                            .load("http://image.tmdb.org/t/p/w185" + peopleResponse.profile_path)
                            .centerInside().resize(people_photo_image_view.width, 150)
                            .placeholder(R.drawable.no_image).into(people_photo_image_view)
                        people_name_text_view.text = peopleResponse.name
                        if (!peopleResponse.biography.isNullOrEmpty()) {
                            dob_text_view.text = formatDateOfBirth(peopleResponse.birthday)
                        } else {
                            dob_text_view.text = "N/A"
                        }
                        biography_text_view.text = peopleResponse.biography
                        val call = request.getPersonMovies (peopleResponse.id)
                        call.enqueue(object: Callback<PersonMovieResponse>{
                            override fun onFailure(call: Call<PersonMovieResponse>, t: Throwable) {
                                Toast.makeText(this@PeopleDetailActivity,t.message,Toast.LENGTH_SHORT).show()
                            }

                            override fun onResponse(
                                call: Call<PersonMovieResponse>,
                                response: Response<PersonMovieResponse>
                            ) {
                                if (response.isSuccessful){
                                    val personMoviesResponse = response.body()
                                    val personMoviesList = personMoviesResponse?.cast
                                    if (personMoviesList != null){
                                        val personMoviesAdapter = PersonMoviesAdapter(personMoviesList){
                                            if (it is PersonMoviesClickEvent.onPersonMovieClicked){
                                                val intent = Intent (this@PeopleDetailActivity, MovieDetailActivity::class.java)
                                                intent.putExtra("MovieId",it.movieId)
                                                startActivity(intent)
                                            }
                                        }
                                        people_credits_recycler_view.layoutManager = LinearLayoutManager(this@PeopleDetailActivity,LinearLayoutManager.HORIZONTAL,false)
                                        people_credits_recycler_view.adapter=personMoviesAdapter

                                    }
                                }
                            }
                        })

                    }
                }
            }
        })
    }

    fun formatDateOfBirth(dob: String?): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var convertedDate: Date? = null
        var formattedDate: String? = null
        try {
            convertedDate = sdf.parse(dob)
            formattedDate = SimpleDateFormat("dd  MMM yyyy").format(convertedDate)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

}



