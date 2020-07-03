package com.android.gsixacademy.mymovies.movie_details.adapters

import com.android.gsixacademy.mymovies.models.CastModel

sealed class CastOnClickEvent {
    data class onCastClickEvent (val cast : CastModel) : CastOnClickEvent()
}