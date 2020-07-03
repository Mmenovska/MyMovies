package com.android.gsixacademy.mymovies.movie_details.adapters

import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.gsixacademy.mymovies.R
import com.android.gsixacademy.mymovies.models.CastModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cast_list_item.view.*


class MovieCastAdapter (val castList : ArrayList<CastModel>, val castClickEvent : (CastOnClickEvent.onCastClickEvent) -> Unit) : RecyclerView.Adapter <RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.cast_list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myViewHolder = holder as MyViewHolder
        myViewHolder.bindData(castList [position],position)
    }

    inner class MyViewHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bindData (itemModel : CastModel,position: Int){
            Picasso.get().load("http://image.tmdb.org/t/p/w185" + itemModel.profile_path).centerInside().resize(itemView.cast_photo_image_view.width,150).placeholder(R.drawable.no_image).into(itemView.cast_photo_image_view)
            itemView.cast_name_text_view.text = itemModel.name
            itemView.character_text_view.text = "As " + itemModel.character
            if (itemModel.gender == 1){
                itemView.cast_gender_text_view.text = "Actress"
            } else {
                itemView.cast_gender_text_view.text = "Actor"
            }
            itemView.cast_main_layout.setOnClickListener {
                castClickEvent.invoke(CastOnClickEvent.onCastClickEvent(itemModel))
            }
        }
    }
}