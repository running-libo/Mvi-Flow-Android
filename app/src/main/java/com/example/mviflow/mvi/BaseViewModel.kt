package com.example.mviflow.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState: IUiState, UiIntent: IUiIntent>: ViewModel() {

    private val _uiIntentFlow: Channel<UiIntent> = Channel()
    val uiIntentFlow: Flow<UiIntent> = _uiIntentFlow.receiveAsFlow()



    init {
        viewModelScope.launch {
            //接收intent事件
            uiIntentFlow.collect {
                handleIntent(it)
            }
        }
    }

    /**
     * 处理view层的intent需求
     */
    protected abstract fun handleIntent(intent: IUiIntent)

    /**
     * 发送Uiintent类型
     */
    fun sendUiIntent(uiIntent: UiIntent) {
        viewModelScope.launch {
            _uiIntentFlow.send(uiIntent)
        }
    }
}