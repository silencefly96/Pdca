package com.silencefly96.pdca.plan

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.silencefly96.pdca.databinding.ItemPlanListBinding
import com.silencefly96.pdca.plan.model.Plan

class PlanAdapter(
    private val planList: List<Plan>,private val onSelectListener: OnSelectListener
): RecyclerView.Adapter<PlanAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemPlanListBinding) : RecyclerView.ViewHolder(binding.root) {
        var title: TextView = binding.title
        var card: CardView = binding.card
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //将绑定类作为参数传入
        val binding = ItemPlanListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plan = planList.get(position)
        //点击事件
        holder.itemView.setOnClickListener{
            onSelectListener.onSelect(plan.id)
        }
        //标题
        holder.title.text = plan.title
        //颜色
        holder.card.setCardBackgroundColor(when(plan.id.toInt()) {
            0 -> Color.parseColor("#0B7BFB")
            1 -> Color.parseColor("#FE4C4C")
            2 -> Color.parseColor("#FF9000")
            3 -> Color.parseColor("#1CD65A")
            else -> Color.parseColor("#CCCCCC")
        })
    }

    override fun getItemCount() = planList.size
}

interface OnSelectListener {
    fun onSelect(id: Long)
}