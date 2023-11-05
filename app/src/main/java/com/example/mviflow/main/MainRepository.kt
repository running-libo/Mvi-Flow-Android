package com.example.mviflow.main

import com.example.mviflow.base.BaseData
import com.example.mviflow.base.BaseRepository
import com.example.mviflow.model.bean.Article
import com.example.mviflow.model.http.WanRetrofitClient
import com.example.mviflow.model.http.service.WanApi


/**
 * 主页Repository
 * @create at 2022 12.12
 * @description:
 **/
class MainRepository : BaseRepository() {

    private val service = WanRetrofitClient.getService(WanApi::class.java)

    suspend fun requestRankData(page: Int): BaseData<Article> {
        return executeRequest { service.getArticle(page) }
    }
}