package com.adworkshop.callbacks

import android.view.View


interface RecyAdapCallBack<T> {
    fun onItemClick(view:View,position:Int,type:Int,selectedRow:Any?)
}