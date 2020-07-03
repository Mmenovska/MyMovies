package com.android.gsixacademy.mymovies.popularMovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.home.HomeFragment
import com.android.gsixacademy.mymovies.people.PeopleFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.explore_item_list.*

class ExploreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.explore_item_list)

        openFragment(HomeFragment.newInstance())
        bottom_navigation_home.setOnNavigationItemSelectedListener ( mNavigationListener )

    }

    private val mNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId){
            R.id.nav_home -> {
                openFragment(HomeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_people -> {
                openFragment(PeopleFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorites -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_watchlist -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                return@OnNavigationItemSelectedListener true
            }
        }


        false
    }

    private fun openFragment (fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}