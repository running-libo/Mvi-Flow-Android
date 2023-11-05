package com.example.mviflow.main

import com.example.mviflow.base.BaseViewModel
import com.example.mviflow.base.IUiIntent
import com.example.mviflow.model.http.WanRetrofitClient
import com.example.mviflow.model.http.service.WanApi

class MainViewModel : BaseViewModel<MainState, MainIntent>() {

    override fun handleIntent(intent: IUiIntent) {
        //viewModel处理Intent，判断intent类型来做对应的事情
        when(intent) {
            is MainIntent.GetDetail -> {
                requestDataWithFlow(
                    showLoading = true,
                    request = {
                        WanRetrofitClient.getService(WanApi::class.java).getArticle(0)
                    },
                    successCallback = { data ->
                        //请求成功进行UI状态回调
                        sendUiState {
                            copy(detailUiSate = DetailUiSate.SUCCESS(data))
                        }
                    }
                )
            }
        }
    }

    override fun initUiState(): MainState {
        return MainState(DetailUiSate.INIT)
    }

}