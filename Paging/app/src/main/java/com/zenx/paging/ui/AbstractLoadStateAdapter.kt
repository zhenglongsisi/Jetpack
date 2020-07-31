package com.zenx.paging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zenx.paging.databinding.AbcLoadStateBinding

/**
 * Created by Long
 */
class AbstractLoadStateAdapter : LoadStateAdapter<AbstractLoadStateAdapter.LoadStateViewHolder>() {

    var onRetry: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = AbcLoadStateBinding.inflate(inflater, parent, false)
        return LoadStateViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindLoadState(loadState)
        holder.viewBinding.root.setOnClickListener { onRetry?.invoke() }
    }

    class LoadStateViewHolder(val viewBinding: AbcLoadStateBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bindLoadState(loadState: LoadState) {
            when (loadState) {
                is LoadState.NotLoading -> {
                    viewBinding.root.isEnabled = false
                    if (loadState.endOfPaginationReached) { //load complete
                        viewBinding.progress.isVisible = false
                        viewBinding.stateText.text = "没有更多数据了"
                    } else {
                        viewBinding.progress.isVisible = true
                        viewBinding.stateText.isVisible = false
                    }
                }
                is LoadState.Loading -> {
                    viewBinding.root.isEnabled = false
                    viewBinding.progress.isVisible = true
                    viewBinding.stateText.isVisible = false
                }
                is LoadState.Error -> {
                    viewBinding.root.isEnabled = true
                    viewBinding.progress.isVisible = false
                    viewBinding.stateText.isVisible = true
                    viewBinding.stateText.text = "加载失败"
                }
            }

        }

    }

}