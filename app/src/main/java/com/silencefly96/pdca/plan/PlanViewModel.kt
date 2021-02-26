package com.silencefly96.pdca.plan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.silencefly96.pdca.plan.model.Plan
import com.silencefly96.pdca.plan.model.PlanRepository

class PlanViewModel: ViewModel() {

    private val type: Int = 1

    lateinit var planRepository: PlanRepository

    val planList = ArrayList<Plan>()

    private val allLiveData = MutableLiveData<Any>()

    val allResult = Transformations.switchMap(allLiveData) {
        planRepository.queryAll(type)
    }

    fun getAll() {
        allLiveData.value = Math.random()
    }

    private val addPlan = MutableLiveData<Plan>()

    val addResult = Transformations.switchMap(addPlan) { plan ->
        planRepository.add(type, plan)
    }

    fun add(id: Long, title: String, content: String) {
        addPlan.value = Plan(id = id, title = title, content = content)
    }

    private val deleteId = MutableLiveData<Long>()

    val deleteResult = Transformations.switchMap(deleteId) { id ->
        planRepository.delete(type, id)
    }

    fun delete(id: Long) {
        deleteId.value = id
    }

    private val queryId = MutableLiveData<Long>()

    val queryResult = Transformations.switchMap(queryId) { id ->
        planRepository.query(type, id)
    }

    fun query(id: Long) {
        queryId.value = id
    }

    private val updatePlan = MutableLiveData<Plan>()

    val updateResult = Transformations.switchMap(updatePlan) { plan ->
        planRepository.update(type, plan)
    }

    fun update(id: Long, title: String, content: String) {
        updatePlan.value = Plan(id = id, title = title, content = content)
    }

}