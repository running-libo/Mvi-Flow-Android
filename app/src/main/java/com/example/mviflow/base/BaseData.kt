package com.example.mviflow.base

import com.google.gson.annotations.SerializedName

/**
 * @create at 2022 12.12
 * @description:
 **/
class BaseData<T> {
    @SerializedName("errorCode")
    var errorCode = -1
    @SerializedName("errorMsg")
    var msg: String? = null
    var data: T? = null
    var state: ReqState = ReqState.Error

    override fun toString(): String {
        return "BaseData(code=$errorCode, msg=$msg, data=$data, state=$state)"
    }
}

enum class ReqState {
    Success, Error
}