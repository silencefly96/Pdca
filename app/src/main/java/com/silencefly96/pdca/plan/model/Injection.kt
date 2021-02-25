package com.silencefly96.pdca.plan.model

import android.content.Context
import com.silencefly96.pdca.plan.model.local.PlanDatabase
import com.silencefly96.pdca.plan.model.local.PlanLocalDataSource
import com.silencefly96.pdca.plan.model.remote.PlanRemoteDataSource

object Injection {

    //方便单元测试
    fun providePlanRepository(context: Context): PlanRepository {
        val database = PlanDatabase.getInstance(context)
        return PlanRepository.getInstance(
            PlanRemoteDataSource.getInstance(),
            PlanLocalDataSource.getInstance(database.planDao())
        )
    }
}