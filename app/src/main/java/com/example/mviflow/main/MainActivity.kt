package com.example.mviflow.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mviflow.R
import com.example.mviflow.model.bean.ArticleItem
import kotlinx.coroutines.flow.map

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btn_loadData).setOnClickListener {
            viewModel.sendUiIntent(MainIntent.GetDetail(0))
        }

        textView = findViewById(R.id.tv_showdata)
    }


}