package com.android.gsixacademy.mymovies

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.gsixacademy.mymovies.home.ExploreActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var mySharedPreferences = null
    var myPreferencesEditor : SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var mySharedPreferences = getSharedPreferences("MyMoviesPreferences", Context.MODE_PRIVATE)

        if (mySharedPreferences.contains("userName")){
            startActivity(Intent(applicationContext, ExploreActivity::class.java))
            finish()
        }

        button_login.setOnClickListener {
            var userName = edit_text_username.text.toString()
            var userPass = edit_text_pass.text.toString()

            mySharedPreferences.edit().putString("userName", userName).apply()
            mySharedPreferences.edit().putString("password", userPass).apply()
            startActivity(Intent(applicationContext, ExploreActivity::class.java))
            finish()
        }
    }
}
