package com.silencefly96.pdca.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.silencefly96.pdca.R
import com.silencefly96.pdca.databinding.ItemPlanListBinding
import com.silencefly96.pdca.plan.model.Plan

class PlanAdapter(
    private val planList: List<Plan>
): RecyclerView.Adapter<PlanAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemPlanListBinding) : RecyclerView.ViewHolder(binding.root) {
        var title: TextView = binding.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlanListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plan = planList.get(position)
        holder.title.text = plan.title
        holder.title.setBackgroundColor(when(plan.state) {
            0 -> R.color.blue
            1 -> R.color.red
            2 -> R.color.orange
            3 -> R.color.green
            else -> R.color.gray
        })
    }

    override fun getItemCount() = planList.size

}