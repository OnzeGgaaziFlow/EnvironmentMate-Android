package com.mtjin.envmate.views.user_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.envmate.base.BaseViewModel
import com.mtjin.envmate.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor() : BaseViewModel() {
    var businessName = MutableLiveData("")
    var businessCode = MutableLiveData("")
    var userName = MutableLiveData("")
    var userRank = MutableLiveData("")
    var userTel = MutableLiveData("")
    var userEmail = MutableLiveData("")

    private val _completeFrom = SingleLiveEvent<Unit>()
    private val _insertUserInfoResult = SingleLiveEvent<Boolean>()

    val completeFrom: LiveData<Unit> get() = _completeFrom
    val insertUserInfoResult: LiveData<Boolean> get() = _insertUserInfoResult

    private val isFormCompleted: Boolean
        get() = (businessCode.value!!.isNotBlank() && businessCode.value!!.isNotBlank() &&
                userName.value!!.isNotBlank() &&
                userRank.value!!.isNotBlank() &&
                userTel.value!!.isNotBlank() &&
                userEmail.value!!.isNotBlank())

    fun completeFrom() {
        _completeFrom.call()
    }

    fun insertUserInfo() {

    }

}