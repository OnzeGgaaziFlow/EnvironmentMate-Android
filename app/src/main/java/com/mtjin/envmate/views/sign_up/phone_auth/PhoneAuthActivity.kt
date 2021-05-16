package com.mtjin.envmate.views.sign_up.phone_auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivityPhoneAuthBinding
import com.mtjin.envmate.views.sign_up.user_info.UserInfoActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class PhoneAuthActivity : BaseActivity<ActivityPhoneAuthBinding>(R.layout.activity_phone_auth) {
    private val viewModel: PhoneAuthViewModel by viewModels()

    private val callbacks by lazy {
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(phoneAuth: PhoneAuthCredential) {
                showToast("인증코드가 전송되었습니다. 90초 이내에 입력해주세요 :)")
                Log.d("AAAAA", "onVerificationCompleted -> " + phoneAuth.smsCode.toString())
                viewModel.phoneAuthNum = phoneAuth.smsCode.toString()
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast("인증실패")
                Log.d("AAAAA", "onVerificationCompleted -> $p0")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            requestPhoneAuth.observe(this@PhoneAuthActivity, Observer {
                if (it) {
                    viewModel.phoneAuthNum = ""
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+82" + viewModel.etPhoneNum.value.toString()
                            .substring(1), // Phone number to verify
                        90, // Timeout duration
                        TimeUnit.SECONDS, // Unit of timeout
                        this@PhoneAuthActivity, // Activity (for callback binding)
                        callbacks
                    )
                    binding.phoneAuthBtnAuth.run {
                        text = "재전송"
                        setTextColor(
                            ContextCompat.getColor(
                                this@PhoneAuthActivity,
                                R.color.dark_gray_333333
                            )
                        )
                        background = ContextCompat.getDrawable(
                            this@PhoneAuthActivity,
                            R.drawable.bg_btn_stroke_dark_gray_333333_radius_8dp
                        )
                    }
                } else {
                    showToast("전화번호를 입력해주세요")
                }
            })

            authComplete.observe(this@PhoneAuthActivity, Observer {
                Log.d("AAAAAAA", "인증 번호-> " + viewModel.phoneAuthNum)
                Log.d("AAAAAAA", "입력한 번호-> " + viewModel.etAuthNum.value.toString())
                if (viewModel.etAuthNum.value!!.isBlank() || (viewModel.etAuthNum.value!! != viewModel.phoneAuthNum)) {
                    binding.phoneAuthTvAuthNum.text = getString(R.string.auth_num_wrong_text)
                    binding.phoneAuthTvAuthNum.setTextColor(
                        ContextCompat.getColor(
                            this@PhoneAuthActivity,
                            R.color.red_FF5050
                        )
                    )
                } else {
                    startActivity(Intent(this@PhoneAuthActivity, UserInfoActivity::class.java))
                }
            })
        }
    }
}