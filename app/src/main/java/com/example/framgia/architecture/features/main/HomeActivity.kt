package com.example.framgia.architecture.features.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.framgia.architecture.R
import com.example.framgia.architecture.features.main.ui.home.HomeFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }

}
