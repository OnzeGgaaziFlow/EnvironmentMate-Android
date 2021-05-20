package com.mtjin.envmate.views.main.chart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.envmate.R
import com.mtjin.envmate.data.model.response.IndustryEnergyRes
import com.mtjin.envmate.databinding.ItemChartMissionBinding

class MissionAdapter(private val onItemClick: (IndustryEnergyRes) -> Unit) :
    RecyclerView.Adapter<MissionAdapter.ViewHolder>() {
    private val items: ArrayList<IndustryEnergyRes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemChartMissionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_chart_mission,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: ItemChartMissionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IndustryEnergyRes) {
            binding.mission = item.result[1][adapterPosition]
            binding.executePendingBindings()
        }
    }

    fun addItems(items: List<IndustryEnergyRes>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: IndustryEnergyRes) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }
}