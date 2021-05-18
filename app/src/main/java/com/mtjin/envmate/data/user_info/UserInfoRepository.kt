package com.mtjin.envmate.data.user_info

import com.mtjin.envmate.data.model.response.SignUpRes
import com.mtjin.envmate.data.model.request.User
import io.reactivex.Single

interface UserInfoRepository {
    fun insertUserInfo(user: User): Single<SignUpRes>
}