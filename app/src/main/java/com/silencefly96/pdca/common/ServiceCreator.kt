package com.silencefly96.pdca.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author: fdk
 * @data: 2020/12/14
 * @description: 创建Service，指定服务器前缀
 */
object ServiceCreator {

    private const val BASE_URL = "http://x.xx.xxx.xx:8800/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}
