package com.mtjin.envmate.views.phone_auth

import android.os.Bundle
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivityPhoneAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneAuthActivity : BaseActivity<ActivityPhoneAuthBinding>(R.layout.activity_phone_auth) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}