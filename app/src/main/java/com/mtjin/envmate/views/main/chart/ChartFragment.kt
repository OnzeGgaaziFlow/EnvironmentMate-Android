package com.mtjin.envmate.views.main.chart

import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
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
        initAdapter()
        initViewModelCallback()
    }

    private fun initAdapter() {
        val alarmAdapter = ChartMissionAdapter(onItemClick = {
            showToast(it.longMission)
        })
        binding.chartRvMissions.adapter = alarmAdapter
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            compareRegionResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
                binding.run {
                    chartTvComment.text = it.result
                    chartLayoutComment.visibility = View.VISIBLE
                    chartLayoutMission.visibility = View.GONE
                }
            })

            compareSameRegionResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
                binding.run {
                    chartTvComment.text = it.result
                    chartLayoutComment.visibility = View.VISIBLE
                    chartLayoutMission.visibility = View.GONE
                }
            })

            compareIndustryAllEnvResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
                binding.run {
                    chartTvComment.text = it.result
                    chartLayoutComment.visibility = View.VISIBLE
                    chartLayoutMission.visibility = View.GONE
                }
            })

            compareIndustrySameAllResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
                binding.run {
                    chartTvComment.text = it.result
                    chartLayoutComment.visibility = View.VISIBLE
                    chartLayoutMission.visibility = View.GONE
                }
            })

            detailIndustryEnergyResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
                binding.run {
                    chartLayoutComment.visibility = View.GONE
                    chartLayoutMission.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun initView() {
        viewModel.requestCompareRegion()
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