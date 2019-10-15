package com.yuejianzhong.imook_flowlayout.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class TagAdapter {
    abstract fun getItemCount():Int
    abstract fun createView(inflater: LayoutInflater, parent: ViewGroup, position: Int):View
    abstract fun bindView(view: View, position: Int)
    open fun onItemViewClick(view: View, position: Int){

    }
    open fun tipForSelectMax(view: View, maxSelectCount: Int){

    }

    /**
     * 设置选择时的 view
     */
    open fun onItemSelected(view: View, position: Int) {

    }

    /**
     * 设置取消的 view
     */
    open fun onItemUnSelected(view: View, position: Int) {

    }

    /**
     * 刷新数据
     */
    open fun notifyDataSetChanged() {
        mListerer?.let {
            mListerer.onDataChanged()
        }
    }

    open fun setOnDataSetChangedListener(listener: OnDataSetChangedListerer) {
        mListerer = listener
    }

    private lateinit var mListerer: OnDataSetChangedListerer

    companion object{
        interface OnDataSetChangedListerer{
            fun onDataChanged()
        }
    }

}