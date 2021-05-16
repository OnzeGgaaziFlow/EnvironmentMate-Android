package com.mtjin.envmate.views.sign_in

import androidx.lifecycle.LiveData
import com.mtjin.envmate.base.BaseViewModel
import com.mtjin.envmate.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : BaseViewModel() {
    private val _goLogin = SingleLiveEvent<Unit>()
    private val _goSignUp = SingleLiveEvent<Unit>()

    val goLogin: LiveData<Unit> get() = _goLogin
    val goSignUp: LiveData<Unit> get() = _goSignUp

    fun goLogin() {
        _goLogin.call()
    }

    fun goSignUp() {
        _goSignUp.call()
    }
}