package com.zenx.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zenx.paging.ui.ArticleFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container,
                ArticleFragment.newInstance()).commit()
        }

    }

}