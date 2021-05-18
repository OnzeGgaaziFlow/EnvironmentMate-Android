/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mtjin.envmate.api

import com.mtjin.envmate.data.model.request.IndustryAllEnvReq
import com.mtjin.envmate.data.model.request.LoginReq
import com.mtjin.envmate.data.model.request.SameRegionEnvReq
import com.mtjin.envmate.data.model.request.User
import com.mtjin.envmate.data.model.response.EnvRes
import com.mtjin.envmate.data.model.response.LoginRes
import com.mtjin.envmate.data.model.response.SignUpRes
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiInterface {

    @POST("accounts/signup-req")
    fun insertUserInfo(@Body user: User): Single<SignUpRes>

    @POST("accounts/login/")
    fun requestLogin(@Body loginReq: LoginReq): Single<LoginRes>

    @POST("datas/compare/region")
    fun requestEntireEnv(): Single<EnvRes>

    @POST("datas/compare/same-region")
    fun requestSameRegionEnv(): Single<EnvRes>

    @POST("datas/compare/industry-all")
    fun requestIndustryAllEnv(): Single<EnvRes>


    companion object {
        private const val BASE_URL = "http://localhost:8000/"

        fun create(): ApiInterface {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BASIC
            }
            val interceptor = Interceptor { chain ->
                with(chain) {
                    val newRequest = request().newBuilder()
                        .addHeader("Authorization", "example")
                        .build()
                    proceed(newRequest)
                }
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}
