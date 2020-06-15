package com.android.gsixacademy.mymovies.popularMovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.gsixacademy.mymovies.R
import kotlinx.android.synthetic.main.explore_item_list.*

class ExploreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        val adapter = ExploreMoviesPagerAdapter (supportFragmentManager)

        view_pager_movies.adapter = adapter
        tab_layout_explore.setupWithViewPager(view_pager_movies)
    }
}