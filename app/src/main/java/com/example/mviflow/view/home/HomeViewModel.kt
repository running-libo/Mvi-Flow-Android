package com.example.mviflow.view.home

import android.util.Log
import com.example.mviflow.mvi.BaseViewModel
import com.example.mviflow.mvi.IUiIntent

class HomeViewModel : BaseViewModel<HomeState, HomeIntent>() {

    /**
     * 处理页面的intent类型
     */
    override fun handleIntent(intent: IUiIntent) {

        when(intent) {
            is HomeIntent.loadData -> {
                Log.i("minfo", "loadDataIntent ------ ")
            }
        }
    }



}