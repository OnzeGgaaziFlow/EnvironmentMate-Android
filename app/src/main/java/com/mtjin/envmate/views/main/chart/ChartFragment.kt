package com.mtjin.envmate.views.main.chart

import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseFragment
import com.mtjin.envmate.databinding.FragmentChartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartFragment :
    BaseFragment<FragmentChartBinding>(R.layout.fragment_chart) {

    private val viewModel: ChartViewModel by viewModels()
    override fun init() {
        binding.vm = viewModel
        initView()
        initViewModelCallback()
    }

    private fun initViewModelCallback() {

    }

    private fun initView() {
        binding.chartSpType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        viewModel.requestCompareRegion()
                    }
                    1 -> {
                        viewModel.requestCompareSameRegion()
                    }
                    2 -> {
                        viewModel.requestCompareIndustryAllEnv()
                    }
                    3 -> {
                        viewModel.requestCompareIndustrySameAll()
                    }
                    4 -> {
                        viewModel.requestDetailIndustryEnergy()
                    }
                }
            }
        }

    }
}