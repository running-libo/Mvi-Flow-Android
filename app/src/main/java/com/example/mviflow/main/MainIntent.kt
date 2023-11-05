package com.example.mviflow.main

import com.example.mviflow.base.IUiIntent

sealed class MainIntent: IUiIntent {

    data class GetDetail(val page: Int): MainIntent()
}