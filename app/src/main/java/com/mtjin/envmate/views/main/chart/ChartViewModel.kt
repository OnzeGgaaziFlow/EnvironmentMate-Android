package com.mtjin.envmate.views.main.chart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.envmate.base.BaseViewModel
import com.mtjin.envmate.data.chart.ChartRepository
import com.mtjin.envmate.data.model.response.EnvRes
import com.mtjin.envmate.data.model.response.IndustryEnergy
import com.mtjin.envmate.data.model.response.IndustryEnergyRes
import com.mtjin.envmate.utils.SingleLiveEvent
import com.mtjin.envmate.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(private val repository: ChartRepository) :
    BaseViewModel() {

    var gas = MutableLiveData("0")
    var other = MutableLiveData("0")
    var oil = MutableLiveData("0")
    var coal = MutableLiveData("0")
    var thermal = MutableLiveData("0")
    var electric = MutableLiveData("0")

    private val _compareRegionResult = SingleLiveEvent<EnvRes>()
    private val _compareSameRegionResult = SingleLiveEvent<EnvRes>()
    private val _compareIndustryAllEnvResult = SingleLiveEvent<EnvRes>()
    private val _compareIndustrySameAllResult = SingleLiveEvent<EnvRes>()
    private val _detailIndustryEnergyResult = SingleLiveEvent<IndustryEnergyRes>()
    private val _industryEnergyList = MutableLiveData<List<IndustryEnergy>>()

    val compareRegionResult: LiveData<EnvRes> get() = _compareRegionResult
    val compareSameRegionResult: LiveData<EnvRes> get() = _compareSameRegionResult
    val compareIndustryAllEnvResult: LiveData<EnvRes> get() = _compareIndustryAllEnvResult
    val compareIndustrySameAllResult: LiveData<EnvRes> get() = _compareIndustrySameAllResult
    val detailIndustryEnergyResult: LiveData<IndustryEnergyRes> get() = _detailIndustryEnergyResult
    val industryEnergyList: LiveData<List<IndustryEnergy>> get() = _industryEnergyList

    fun requestCompareRegion() {
        repository.requestCompareRegion(
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribeBy(onSuccess = {
                Log.d(TAG, "requestCompareRegion() onSuccess -> $it")
                _compareRegionResult.value = it

            }, onError = {
                Log.d(TAG, "requestCompareRegion() onError -> " + it.localizedMessage)
            }).addTo(compositeDisposable)
    }

    fun requestCompareSameRegion() {
        repository.requestCompareSameRegion(
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribeBy(onSuccess = {
                Log.d(TAG, "requestCompareSameRegion() onSuccess -> $it")
                _compareSameRegionResult.value = it
            }, onError = {
                Log.d(TAG, "requestCompareSameRegion() onError -> " + it.localizedMessage)
            }).addTo(compositeDisposable)
    }

    fun requestCompareIndustryAllEnv() {
        repository.requestCompareIndustryAllEnv(
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribeBy(onSuccess = {
                Log.d(TAG, "requestCompareIndustryAllEnv() onSuccess -> $it")
                _compareIndustryAllEnvResult.value = it
            }, onError = {
                Log.d(TAG, "requestCompareIndustryAllEnv() onError -> " + it.localizedMessage)
            }).addTo(compositeDisposable)
    }

    fun requestCompareIndustrySameAll() {
        repository.requestCompareIndustryAllEnv(
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribeBy(onSuccess = {
                Log.d(TAG, "requestCompareIndustrySameAll() onSuccess -> $it")
                _compareIndustrySameAllResult.value = it
            }, onError = {
                Log.d(TAG, "requestCompareIndustrySameAll() onError -> " + it.localizedMessage)
            }).addTo(compositeDisposable)
    }

    fun requestDetailIndustryEnergy() {
        repository.requestDetailIndustryEnergy(
            gas.value!!.toInt(),
            other.value!!.toInt(),
            oil.value!!.toInt(),
            coal.value!!.toInt(),
            thermal.value!!.toInt(),
            electric.value!!.toInt()
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribeBy(onSuccess = {
                Log.d(TAG, "requestDetailIndustryEnergy() onSuccess -> $it")
                _detailIndustryEnergyResult.value = it
                val itemList = ArrayList<IndustryEnergy>()
                for (i in 0..it.result.size) {
                    val longMission = it.result[0][i]
                    val shortMission = it.result[1][i]
                    itemList.add(IndustryEnergy(longMission, shortMission))
                }
                _industryEnergyList.value = itemList
            }, onError = {
                Log.d(TAG, "requestDetailIndustryEnergy() onError -> " + it.localizedMessage)
            }).addTo(compositeDisposable)
    }

}