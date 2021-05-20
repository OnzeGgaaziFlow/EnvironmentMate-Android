package com.mtjin.envmate.data.chart

import com.mtjin.envmate.api.ApiInterface
import com.mtjin.envmate.data.model.response.EnvRes
import com.mtjin.envmate.data.model.response.IndustryEnergyRes
import io.reactivex.Single
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) :
    ChartRepository {
    override fun requestCompareRegion(): Single<EnvRes> {
        return apiInterface.requestCompareRegion()
    }

    override fun requestCompareSameRegion(usage: Int): Single<EnvRes> {
        return apiInterface.requestCompareSameRegion(usage)
    }

    override fun requestCompareIndustryAllEnv(): Single<EnvRes> {
        return apiInterface.requestCompareIndustryAllEnv()
    }

    override fun requestCompareIndustrySameAll(usage: Int): Single<EnvRes> {
        return apiInterface.requestCompareIndustrySameAll(usage)
    }

    override fun requestDetailIndustryEnergy(
        gas: Int,
        other: Int,
        oil: Int,
        coal: Int,
        thermal: Int,
        electric: Int
    ): Single<IndustryEnergyRes> {
        return apiInterface.requestDetailIndustryEnergy(gas, other, oil, coal, thermal, electric)
    }
}