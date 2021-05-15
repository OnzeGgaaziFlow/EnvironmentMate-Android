package com.mtjin.envmate.views.sign_in

import androidx.lifecycle.LiveData
import com.mtjin.envmate.base.BaseViewModel
import com.mtjin.envmate.utils.SingleLiveEvent

class SignInViewModel : BaseViewModel() {
    private val _goLogin = SingleLiveEvent<Unit>()
    private val _goSignUp = SingleLiveEvent<Unit>()

    val goLogin: LiveData<Unit> = _goLogin
    val goSignUp: LiveData<Unit> = _goSignUp

    fun goLogin() {
        _goLogin.call()
    }

    fun goSignUp() {
        _goSignUp.call()
    }
}