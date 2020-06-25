package com.android.gsixacademy.mymovies.popularMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.models.MovieResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MoviesListAdapter (val moviesList : ArrayList<MovieResult>, val moviesListOnClickEvent : (MoviesOnClickEvent) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
var myHolder = holder as MyViewHolder
        myHolder.bindData(moviesList[position],position)
    }

    inner class MyViewHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bindData (itemModel : MovieResult, position: Int){
            Picasso.get().load("http://image.tmdb.org/t/p/w185" + itemModel.poster_path).fit().into(itemView.movie_image_view)
            var rating = 0.0

            if (itemModel.vote_average != null){
                rating = itemModel.vote_average.toDouble()
                if ( rating < 4){
                    Picasso.get().load(R.drawable.rate_02_hdpi).fit().into(itemView.rating_image_view)
                } else if (rating > 4 && rating < 6){
                    Picasso.get().load(R.drawable.rate_04_hdpi).fit().into(itemView.rating_image_view)
                } else if ( rating > 6 && rating < 8){
                    Picasso.get().load(R.drawable.rate_06_hdpi).fit().into(itemView.rating_image_view)
                } else {
                    Picasso.get().load(R.drawable.rate_08_hdpi).fit().into (itemView.rating_image_view)

                }
            }
            itemView.text_view_rating.setText(itemModel.vote_average + "/10")
            itemView.text_view_watchlist.setText("Add to watchlist")
            itemView.text_view_title.text = itemModel.title

            itemView.main_layout.setOnClickListener (View.OnClickListener {
                moviesListOnClickEvent.invoke(MoviesOnClickEvent.onMovieClicked(itemModel))
            }) }

        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent,false))
    }


}