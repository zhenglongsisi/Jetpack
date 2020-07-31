package com.zenx.paging.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.zenx.paging.data.ArticlePagingSource

/**
 * Created by Long
 */
class ArticleViewModel @ViewModelInject constructor(private val articleSource: ArticlePagingSource) :
    ViewModel() {

    fun articles() = Pager(
        config = PagingConfig(20)
    ) { articleSource }.flow

}