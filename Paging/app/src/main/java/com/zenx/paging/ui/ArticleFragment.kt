package com.zenx.paging.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.zenx.paging.databinding.FragmentPagingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Long
 */
@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private lateinit var mViewBinding: FragmentPagingBinding

    private val mViewModel: ArticleViewModel by viewModels()

    private val mPagingAdapter = ArticleAdapter()
    private val mLoadStateAdapter = AbstractLoadStateAdapter().apply {
        onRetryListener = { mPagingAdapter.retry() }
    }
    private val mConcatAdapter: ConcatAdapter =
        mPagingAdapter.withLoadStateFooter(mLoadStateAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = FragmentPagingBinding.inflate(inflater, container, false)
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mViewBinding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mConcatAdapter
        }

    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            mViewModel.articles().collectLatest {
                mPagingAdapter.submitData(it)
            }
        }
    }

    companion object {
        private const val TAG = "PagingFragment"
        fun newInstance() = ArticleFragment()
    }

}