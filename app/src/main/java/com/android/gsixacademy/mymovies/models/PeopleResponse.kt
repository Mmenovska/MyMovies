package com.android.gsixacademy.mymovies.models

class PeopleResponse(
    val page : Int?,
    val total_results : Int?,
    val total_pages : Int?,
    val results : ArrayList<CastModel>
)