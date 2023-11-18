package com.example.mviflow

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mviflow.databinding.ActivityMain3Binding
import com.example.mviflow.main.DetailUiSate
import com.example.mviflow.main.MainIntent
import com.example.mviflow.main.MainViewModel
import com.example.mviflow.model.bean.ArticleItem
import com.example.mviflow.base.BaseBindingActivity
import kotlinx.coroutines.flow.map

class MainActivity3 : BaseBindingActivity<ActivityMain3Binding>({
    ActivityMain3Binding.inflate(it)
}) {
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        findViewById<Button>(R.id.btn_loadData).setOnClickListener {
            viewModel.sendUiIntent(MainIntent.GetDetail(0))
        }



        textView = findViewById(R.id.tv_showdata)
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData(savedInstanceState: Bundle?) {

        lifecycleScope.launchWhenStarted {

            //请求数据flow的流的监听和处理
            viewModel.uiStateFlow.map { it.detailUiSate }
                .collect { detailUiState ->
                    when(detailUiState) {
                        is DetailUiSate.SUCCESS -> {
                            showText(detailUiState.articles.datas)
                        }
                        else -> {}
                    }
                }
        }
    }

    private fun showText(item: List<ArticleItem>) {
        val sb = java.lang.StringBuilder()
        item.forEach {
            sb.append(it.title + "   " + it.niceDate + "\n")
        }
        textView?.text = sb.toString()
    }
}