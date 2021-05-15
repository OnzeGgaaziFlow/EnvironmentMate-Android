package com.mtjin.envmate.views.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mtjin.envmate.R
import com.mtjin.envmate.views.sign_in.SignInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        goSignIn()
    }

    private fun goSignIn() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }, 1000)
    }
}