package com.example.mviflow.base

import androidx.annotation.Keep

@Keep
interface IUiState

@Keep
interface IUiIntent

sealed class LoadUiIntent {
    data class Loading(var isShow: Boolean): LoadUiIntent()

    object showMainView : LoadUiIntent()

    data class Error(val msg: String) : LoadUiIntent()
}