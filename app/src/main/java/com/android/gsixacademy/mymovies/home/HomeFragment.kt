package com.android.gsixacademy.mymovies.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.popularMovies.ExploreMoviesPagerAdapter
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object{
        fun newInstance () : HomeFragment = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ExploreMoviesPagerAdapter(activity!!.supportFragmentManager)
        view_pager_movies.adapter = adapter
        tab_layout_explore.setupWithViewPager(view_pager_movies)

    }




}