package com.silencefly96.pdca.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.silencefly96.pdca.databinding.FragmentPlanDetailBinding
import com.silencefly96.pdca.plan.model.Plan

class PlanDetailFragment(private val viewModel: PlanViewModel): Fragment() {

    private var _binding: FragmentPlanDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanDetailBinding.inflate(inflater, container, false)

        //设置绑定数据
        binding.viewModel = viewModel

        //监听选择id变化
        viewModel.currentId.observe(viewLifecycleOwner, { id ->
            if(id >= 0) {
                binding.button.text = "更新"
                //更新数据
                viewModel.queryId.value = id
                binding.button.setOnClickListener{
                    viewModel.updatePlan.value = viewModel.currentPlan.value
                }
            }else {
                binding.button.text = "新增"
                //将绑定数据设置成新数据
                viewModel.currentPlan.value = Plan()
                //手动刷新一下。。。
                binding.viewModel = viewModel
                binding.button.setOnClickListener {
                    viewModel.addPlan.value = viewModel.currentPlan.value
                }
            }
        })

        viewModel.queryResult.observe(viewLifecycleOwner, { result ->
            if (result.isFailure) {
                Toast.makeText(activity, "更新数据失败", Toast.LENGTH_SHORT).show()
            }

            val plan = result.getOrNull()
            plan?.let {
                //获取到新数据的时候，更新绑定数据
                viewModel.currentPlan.value = it
                //手动刷新一下。。。
                binding.viewModel = viewModel
            }
        })

        viewModel.updateResult.observe(viewLifecycleOwner, {
            if (it.isFailure) {
                Toast.makeText(activity, "获取数据失败", Toast.LENGTH_SHORT).show()
            }

            val result = it.getOrNull()
            result?.let {id ->
                Toast.makeText(activity, "更新数据成功: id = $id", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.addResult.observe(viewLifecycleOwner, {
            if (it.isFailure) {
                Toast.makeText(activity, "获取数据失败", Toast.LENGTH_SHORT).show()
            }

            val result = it.getOrNull()
            result?.let {id ->
                Toast.makeText(activity, "添加数据成功: id = $id", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(viewModel: PlanViewModel) = PlanDetailFragment(viewModel)
    }
}