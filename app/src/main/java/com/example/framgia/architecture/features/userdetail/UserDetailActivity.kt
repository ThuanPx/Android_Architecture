package com.example.framgia.architecture.features.userdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.framgia.architecture.R
import com.example.framgia.architecture.features.userdetail.ui.userdetail.UserDetailFragment

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserDetailFragment.newInstance())
                .commitNow()
        }
    }

}
