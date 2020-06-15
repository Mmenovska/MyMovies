package com.android.gsixacademy.mymovies.popularMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ExploreMoviesPagerAdapter ( fragmentManager : FragmentManager) : FragmentStatePagerAdapter (fragmentManager) {

    override fun getItem(position: Int): Fragment {
      var fragment : Fragment
        var args = Bundle()
        args.putString("category", getCategory(position))

        fragment = MoviesFragment()
        fragment.arguments = args
        return fragment
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Popular"
            1 -> return "Top Rated"
            2 -> "Upcoming"
            3 -> return "Now Playing"


        }
        return ""
    }




    fun getCategory (position: Int) : String {
        when (position){
            0 -> "popular"
            1 -> "top_rated"
            2 -> "upcoming"
            3 -> "now_playing"
        }
        return ""
    }
}