package com.silencefly96.pdca.plan.model

import androidx.room.Entity
import androidx.room.PrimaryKey


//8.129.134.62:8800/tasks/query

@Entity(tableName = "plan")
data class Plan constructor(

        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,

        //状态：0 - P，1 - D，2 - C，3 - A，4 - END
        var state: Int = 0,

        //标签：
        var filter: Int = 0,

        //类型:0 - 一般任务，1 - 周期性任务
        var type: Int = 0,

        //开始时间
        var startTime: String = "20210224",

        //周期间隔：
        var duration: Long = 0,

        //结束时间
        var endTime: String = "20210224",

        //标题
        var title: String = "",

        //内容
        var content: String = ""
)