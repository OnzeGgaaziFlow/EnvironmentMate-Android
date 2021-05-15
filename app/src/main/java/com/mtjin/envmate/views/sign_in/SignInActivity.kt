package com.mtjin.envmate.views.sign_in

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivitySignInBinding
import com.mtjin.envmate.views.login.LoginActivity
import com.mtjin.envmate.views.sign_up.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goLogin.observe(this@SignInActivity, {
                startActivity(Intent(this@SignInActivity, LoginActivity::class.java))
            })

            goSignUp.observe(this@SignInActivity, {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            })
        }
    }
}