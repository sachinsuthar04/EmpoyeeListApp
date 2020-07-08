package com.example.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.base.BaseActivity
import com.example.empoyeelistapp.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initToolbar(false)

        Handler().postDelayed({
            startActivity(Intent(context, HomeActivity::class.java))
            finishAffinity()
        }, 2000)
    }
}