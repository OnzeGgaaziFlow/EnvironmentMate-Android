package com.mtjin.envmate.data.user_info

import com.mtjin.envmate.api.ApiInterface
import com.mtjin.envmate.data.model.response.SignUpRes
import com.mtjin.envmate.data.model.request.User
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) :
    UserInfoRepository {
    override fun insertUserInfo(user: User): Single<SignUpRes> {
        return apiInterface.insertUserInfo(user)
    }
}