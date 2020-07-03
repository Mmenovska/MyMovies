package com.android.gsixacademy.mymovies.movie_details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.models.MovieCreditsResponse
import com.android.gsixacademy.mymovies.movie_details.adapters.CastOnClickEvent
import com.android.gsixacademy.mymovies.movie_details.adapters.MovieCastAdapter
import com.android.gsixacademy.mymovies.people.PeopleDetailActivity
import kotlinx.android.synthetic.main.activity_movie_full_cast.*

class MovieFullCastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_full_cast)

        val castModel = intent.getSerializableExtra("movieCast") as MovieCreditsResponse

        val castAdapter = MovieCastAdapter(castModel.cast){
            if (it is CastOnClickEvent.onCastClickEvent) {
                val intent = Intent(this@MovieFullCastActivity, PeopleDetailActivity::class.java)
                intent.putExtra("peopleId", it.cast.id)
                startActivity(intent)
            }
        }
        cast_recycler_view.layoutManager = LinearLayoutManager (this)
        cast_recycler_view.adapter = castAdapter

    }
}
