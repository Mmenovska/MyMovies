package com.android.gsixacademy.mymovies.people.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.models.PersonMoviesModel
import com.android.gsixacademy.mymovies.movie_details.adapters.MovieCastAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.person_movies_item_list.view.*

class PersonMoviesAdapter (val personMoviesList : ArrayList<PersonMoviesModel>, val onPersonMovieClick : (PersonMoviesClickEvent) -> Unit ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.person_movies_item_list,parent,false))
    }

    override fun getItemCount(): Int {
        return personMoviesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.bindData(personMoviesList[position],position)
    }

    inner class MyViewHolder (view : View) : RecyclerView.ViewHolder(view){
        fun bindData (itemModel : PersonMoviesModel, position: Int){
            Picasso.get().load("http://image.tmdb.org/t/p/w185" + itemModel.poster_path).centerInside().resize(150,200).placeholder(R.drawable.no_image).into(itemView.person_movies_image_view)
            itemView.person_movies_title_text_view.text=itemModel.title
            itemView.person_movies_main_layout.setOnClickListener {
                onPersonMovieClick.invoke(PersonMoviesClickEvent.onPersonMovieClicked(itemModel.id))
            }
        }
    }

}