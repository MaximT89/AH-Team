package com.ahinfo.ahteam.core.bases

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract fun title() : String
}