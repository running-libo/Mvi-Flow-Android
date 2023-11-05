package com.example.mviflow.base

/**
 * 返回数据的封装
 */
open class BaseRepository {

    suspend fun <T : Any> executeRequest(block: suspend() -> BaseData<T>): BaseData<T> {
        val baseData = block.invoke()
        if (baseData.errorCode == 0) {
            baseData.state = ReqState.Success
        } else {
            baseData.state = ReqState.Error
        }
        return baseData
    }
}