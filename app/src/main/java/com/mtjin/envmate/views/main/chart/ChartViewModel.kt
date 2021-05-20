package com.mtjin.envmate.views.main.chart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.envmate.base.BaseViewModel
import com.mtjin.envmate.data.chart.ChartRepository
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

    var gas = MutableLiveData("")
    var other = MutableLiveData("")
    var oil = MutableLiveData("")
    var coal = MutableLiveData("")
    var thermal = MutableLiveData("")
    var electric = MutableLiveData("")

    private val _compareRegionResult = SingleLiveEvent<Boolean>()
    private val _compareSameRegionResult = SingleLiveEvent<Boolean>()
    private val _compareIndustryAllEnvResult = SingleLiveEvent<Boolean>()
    private val _compareIndustrySameAllResult = SingleLiveEvent<Boolean>()
    private val _detailIndustryEnergyResult = SingleLiveEvent<Boolean>()

    val compareRegionResult: LiveData<Boolean> get() = _compareRegionResult
    val compareSameRegionResult: LiveData<Boolean> get() = _compareSameRegionResult
    val compareIndustryAllEnvResult: LiveData<Boolean> get() = _compareIndustryAllEnvResult
    val compareIndustrySameAllResult: LiveData<Boolean> get() = _compareIndustrySameAllResult
    val detailIndustryEnergyResult: LiveData<Boolean> get() = _detailIndustryEnergyResult

    fun requestCompareRegion() {
        repository.requestCompareRegion(
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribeBy(onSuccess = {
                Log.d(TAG, "requestCompareRegion() onSuccess -> $it")
                _compareRegionResult.value = true
            }, onError = {
                Log.d(TAG, "requestCompareRegion() onError -> " + it.localizedMessage)
                _compareRegionResult.value = false
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
                _compareSameRegionResult.value = true
            }, onError = {
                Log.d(TAG, "requestCompareSameRegion() onError -> " + it.localizedMessage)
                _compareSameRegionResult.value = false
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
                _compareIndustryAllEnvResult.value = true
            }, onError = {
                Log.d(TAG, "requestCompareIndustryAllEnv() onError -> " + it.localizedMessage)
                _compareIndustryAllEnvResult.value = false
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
                _compareIndustrySameAllResult.value = true
            }, onError = {
                Log.d(TAG, "requestCompareIndustrySameAll() onError -> " + it.localizedMessage)
                _compareIndustrySameAllResult.value = false
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
                _detailIndustryEnergyResult.value = true
            }, onError = {
                Log.d(TAG, "requestDetailIndustryEnergy() onError -> " + it.localizedMessage)
                _detailIndustryEnergyResult.value = false
            }).addTo(compositeDisposable)
    }

}