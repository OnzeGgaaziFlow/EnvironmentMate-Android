package com.mtjin.envmate.api

import android.util.Log
import com.mtjin.envmate.data.model.response.EnvRes
import com.mtjin.envmate.data.model.response.IndustryEnergyRes
import com.mtjin.envmate.utils.UserInfo
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiInterface {

    @GET("datas/compare/region")
    fun requestCompareRegion(): Single<EnvRes>

    @GET("datas/compare/same-region")
    fun requestCompareSameRegion(
        @Query("usage") usage: Int = 80000
    ): Single<EnvRes>

    @GET("datas/compare/industry-all")
    fun requestCompareIndustryAllEnv(): Single<EnvRes>

    @GET("datas/compare/industry-sameall")
    fun requestCompareIndustrySameAll(@Query("usage") usage: Int): Single<EnvRes>

    @GET("datas/detail/industry-energy")
    fun requestDetailIndustryEnergy(
        @Query("gas") gas: Float,
        @Query("other") other: Float,
        @Query("oil") oil: Float,
        @Query("coal") coal: Float,
        @Query("thermal") thermal: Float,
        @Query("electric") electric: Float
    ): Single<IndustryEnergyRes>

    companion object {
        private const val BASE_URL =
            "http://7324609a5ddf.ngrok.io/"

        fun create(): MainApiInterface {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BASIC
            }
            val interceptor = Interceptor { chain ->
                with(chain) {
                    val newRequest = request().newBuilder()
                        .addHeader("Authorization", "Token " + UserInfo.headerKey)
                        .build()
                    proceed(newRequest)
                }
            }
            Log.d("AAAAA", "Token " + UserInfo.headerKey)
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
                .create(MainApiInterface::class.java)
        }
    }
}