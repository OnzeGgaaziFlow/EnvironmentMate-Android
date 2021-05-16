package com.mtjin.envmate.views.phone_auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.envmate.base.BaseViewModel

class PhoneAuthViewModel : BaseViewModel() {
    val etPhoneNum = MutableLiveData<String>("")
    val etAuthNum = MutableLiveData<String>("")

    private val _requestPhoneAuth = MutableLiveData<Boolean>()

    val requestPhoneAuth: LiveData<Boolean> get() = _requestPhoneAuth

    fun requestPhoneAuth() {
        _requestPhoneAuth.value = !etPhoneNum.value.isNullOrBlank()
    }
}