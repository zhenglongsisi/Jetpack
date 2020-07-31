package com.zenx.paging.data

import androidx.paging.PagingSource
import com.zenx.paging.api.ApiService
import com.zenx.paging.model.Article
import javax.inject.Inject

/**
 * Created by Long
 */
class ArticlePagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response = apiService.articles(page)
            if (response.errorCode == 0) {
                LoadResult.Page(
                    response.data.list,
                    null,
                    page + 1
                )
            } else {
                LoadResult.Error(Exception(response.errorMessage))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

}