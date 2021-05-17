package com.silencefly96.pdca.plan

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.silencefly96.pdca.plan.model.Plan
import com.silencefly96.pdca.plan.model.PlanRepository
import kotlinx.coroutines.Dispatchers

/*
 * 计划的数据仓库
 * 为什么要写这么复杂？考虑下事件的触发和事件的监听分离的情况，一处修改，处处更新
 */
class PlanViewModel: ViewModel() {

    //类型id， 0 - 本地，1 - 远程
    private val type: Int = 0

    //仓库
    lateinit var planRepository: PlanRepository

    //数据列表
    val planList = ArrayList<Plan>()

    var currentPlan = MutableLiveData<Plan>()

    //当前操作id
    val currentId = MutableLiveData<Long>()

    //获取所有数据
    val allLiveData = MutableLiveData<Any>()
    val allResult = Transformations.switchMap(allLiveData) {
        planRepository.queryAll(type)
    }

    //增加数据
    val addPlan = MutableLiveData<Plan>()
    val addResult = Transformations.switchMap(addPlan) { plan ->
        planRepository.add(type, plan)
    }

    //删除数据
    val deleteId = MutableLiveData<Long>()
    val deleteResult = Transformations.switchMap(deleteId) { id ->
        planRepository.delete(type, id)
    }

    //查询数据
    val queryId = MutableLiveData<Long>()
    val queryResult = Transformations.switchMap(queryId) { id ->
        planRepository.query(type, id)
    }

    //更新数据
    val updatePlan = MutableLiveData<Plan>()
    val updateResult = Transformations.switchMap(updatePlan) { plan ->
        planRepository.update(type, plan)
    }

}