package com.mtjin.envmate.views.user_info

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivityUserInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoActivity : BaseActivity<ActivityUserInfoBinding>(R.layout.activity_user_info) {
    private val viewModel: UserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            completeFrom.observe(this@UserInfoActivity, Observer {
                binding.run {
                    when {
                        businessName.value!!.isBlank() -> {
                            userInfoEtBusinessName.requestFocus()
                        }
                        businessCode.value!!.isBlank() -> {
                            userInfoEtBusinessCode.requestFocus()
                        }
                        userName.value!!.isBlank() -> {
                            userInfoEtUserName.requestFocus()
                        }
                        userRank.value!!.isBlank() -> {
                            userInfoEtUserRank.requestFocus()
                        }
                        userTel.value!!.isBlank() -> {
                            userInfoEtUserPhone.requestFocus()
                        }
                        userEmail.value!!.isBlank() -> {
                            userInfoEtUserEmail.requestFocus()
                        }
                        else -> {
                            insertUserInfo()
                        }
                    }
                }
            })

        }
    }
}