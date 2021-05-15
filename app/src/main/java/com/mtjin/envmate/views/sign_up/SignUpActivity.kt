package com.mtjin.envmate.views.sign_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivitySignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}