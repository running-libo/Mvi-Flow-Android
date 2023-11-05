package com.example.mviflow.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open abstract class BaseViewModel<UiState: IUiState, UiIntent: IUiIntent> : ViewModel(){

    private val _uiStateFlow = MutableStateFlow(initUiState())
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow

    protected abstract fun initUiState(): UiState

    protected fun sendUiState(copy: UiState.() -> UiState) {
        _uiStateFlow.update {
            copy(_uiStateFlow.value)
        }
    }

    private val _uiIntentFlow: Channel<UiIntent> = Channel()
    val uiintentFlow: Flow<UiIntent> = _uiIntentFlow.receiveAsFlow()

    fun sendUiIntent(uiIntent: UiIntent) {
        viewModelScope.launch {
            _uiIntentFlow.send(uiIntent)
        }
    }

    init {
        viewModelScope.launch {
            uiintentFlow.collect {
                handleIntent(it)
            }
        }
    }

    //向view层回调intent
    protected abstract fun handleIntent(intent: IUiIntent)

    /**
     * 用高阶函数自定义实现各自的请求和返回数据
     */
    protected fun <T: Any> requestDataWithFlow(
        showLoading: Boolean = true,
        request: suspend () -> BaseData<T>,
        successCallback: (T) -> Unit,
        failCallback: suspend (String) -> Unit = { errorMsg ->

        }
    ) {
        //开启协程
        viewModelScope.launch {
            val baseData = request()  //做自定义请求返回baseData
            when(baseData.state) {
                ReqState.Success -> {
                    baseData.data?.let {
                        successCallback(it)
                    }
                }
                ReqState.Error -> {
                    baseData.msg?.let {
                        error(it)
                    }
                }
            }

        }
    }
}