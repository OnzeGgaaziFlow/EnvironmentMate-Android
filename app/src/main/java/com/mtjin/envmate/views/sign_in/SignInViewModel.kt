package com.mtjin.envmate.views.sign_in

import androidx.lifecycle.LiveData
import com.mtjin.envmate.base.BaseViewModel
import com.mtjin.envmate.utils.SingleLiveEvent

class SignInViewModel : BaseViewModel() {
    private val _goLogin = SingleLiveEvent<Unit>()

    val goLogin: LiveData<Unit> = _goLogin

    fun goLogin() {
        _goLogin.call()
    }
}