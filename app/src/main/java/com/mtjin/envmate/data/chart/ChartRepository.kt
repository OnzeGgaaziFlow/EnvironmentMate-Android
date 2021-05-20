package com.mtjin.envmate.data.chart

import com.mtjin.envmate.data.model.response.EnvRes
import com.mtjin.envmate.data.model.response.IndustryEnergyRes
import io.reactivex.Single

interface ChartRepository {
    fun requestCompareRegion(): Single<EnvRes>
    fun requestCompareSameRegion(
        usage: Int = 80000
    ): Single<EnvRes>

    fun requestCompareIndustryAllEnv(): Single<EnvRes>
    fun requestCompareIndustrySameAll(usage: Int): Single<EnvRes>

    fun requestDetailIndustryEnergy(
        gas: Float,
        other: Float,
        oil: Float,
        coal: Float,
        thermal: Float,
        electric: Float
    ): Single<IndustryEnergyRes>

}