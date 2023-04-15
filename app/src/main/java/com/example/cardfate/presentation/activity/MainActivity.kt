package com.example.cardfate.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.cardfate.R
import com.example.cardfate.presentation.fragment.SignInFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkUserSigned()
    }

    private fun checkUserSigned() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val login = pref.getString(LOGIN, null)
        if (login == null) {
            navigateToFragment(SignInFragment())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    companion object {

        const val LOGIN = "login"
    }
}