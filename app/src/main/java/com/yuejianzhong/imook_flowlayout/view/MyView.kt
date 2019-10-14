package com.yuejianzhong.imook_flowlayout.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View

class MyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        initA()
    }

    private fun initA() {
        Log.d("aa", "aa")
    }
}
