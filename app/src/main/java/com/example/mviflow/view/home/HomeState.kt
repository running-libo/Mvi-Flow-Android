package com.example.mviflow.view.home

import com.example.mviflow.model.bean.Article
import com.example.mviflow.mvi.IUiState

//home页面状态
data class HomeState(val dataState: DataState) : IUiState

//home页面数据状态
sealed class DataState {
    object INIT: DataState()
    data class SUCCESS(val articles: Article): DataState()
}