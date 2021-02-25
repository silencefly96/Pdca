package com.silencefly96.pdca.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.silencefly96.pdca.databinding.FragmentPlanListBinding
import com.silencefly96.pdca.R

class PlanListFragment(viewModel: PlanViewModel): Fragment() {

    private var _binding: FragmentPlanListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPlanListBinding.inflate(inflater, container, false)

        binding.text.text = getString(R.string.app_name)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(viewModel: PlanViewModel) = PlanListFragment(viewModel)
    }
}