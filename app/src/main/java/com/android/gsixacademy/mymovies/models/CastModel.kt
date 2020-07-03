package com.android.gsixacademy.mymovies.models

import java.io.Serializable

class CastModel (
    val id : Int?,
    val character : String?,
    val profile_path : String,
    val name : String?,
    val gender : Int?
) : Serializable