package com.android.gsixacademy.mymovies.models

import java.io.Serializable

class MovieCreditsResponse(
    val id : Int?,
    val cast : ArrayList <CastModel>,
val crew : ArrayList<CrewModel>
) : Serializable
