package com.example.mviflow.view.home

import com.example.mviflow.mvi.IUiIntent

/**
 * Home页面Intent封装
 */
sealed class HomeIntent: IUiIntent {

    data class loadData(val page: Int): HomeIntent()
}