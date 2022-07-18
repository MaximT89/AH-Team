package com.ahinfo.ahteam.ui

import android.os.Bundle
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}