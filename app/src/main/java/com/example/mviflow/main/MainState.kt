package com.example.mviflow.main

import com.example.mviflow.base.IUiState
import com.example.mviflow.model.bean.Article

data class MainState(val detailUiSate: DetailUiSate): IUiState

sealed class DetailUiSate {
    object INIT: DetailUiSate()

    data class SUCCESS(val articles: Article): DetailUiSate()
}