package com.silencefly96.pdca.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.silencefly96.pdca.databinding.FragmentPlanListBinding

class PlanListFragment(private val viewModel: PlanViewModel): Fragment() {

    private var _binding: FragmentPlanListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: PlanAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPlanListBinding.inflate(inflater, container, false)

        val layoutManager = LinearLayoutManager(activity)
        binding.list.layoutManager = layoutManager
        adapter = PlanAdapter(viewModel.planList)
        binding.list.adapter = adapter

        viewModel.allResult.observe(viewLifecycleOwner, {result ->
            if (result.isFailure) {
                Toast.makeText(context, "get all plan fail", Toast.LENGTH_SHORT).show()
            }

            val plans = result.getOrNull()
            plans?.let {
                viewModel.planList.clear()
                viewModel.planList.addAll(plans)
                viewModel.planList.sortBy{
                    it.state
                }
                adapter.notifyDataSetChanged()
            }
        })



        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(viewModel: PlanViewModel) = PlanListFragment(viewModel)
    }


}