package com.mtjin.envmate.views.sign_in

import android.os.Bundle
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivitySignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}