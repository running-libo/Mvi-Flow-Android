package com.example.mviflow.model.http.service

import com.example.mviflow.base.BaseData
import com.example.mviflow.model.bean.Article
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @create at 2022 12.12
 * @description:
 **/
interface WanApi {

    //页码从0开始
    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int): BaseData<Article>
}