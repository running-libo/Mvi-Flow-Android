package com.example.mviflow.view.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mviflow.base.BaseBindingFragment
import com.example.mviflow.databinding.FragmentHomeBinding

class HomeFragment : BaseBindingFragment<FragmentHomeBinding>({
    FragmentHomeBinding.inflate(it)
}) {

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        //发送load的intent，且page=0
        viewModel.sendUiIntent(HomeIntent.loadData(0))
    }

    override fun initData(savedInstanceState: Bundle?) {

    }
}