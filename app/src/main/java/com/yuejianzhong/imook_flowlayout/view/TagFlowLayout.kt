package com.yuejianzhong.imook_flowlayout.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import java.time.chrono.MinguoDate

class TagFlowLayout(context: Context, attrs: AttributeSet? = null) :
    FlowLayout(context, attrs), TagAdapter.Companion.OnDataSetChangedListerer {
    var adapter:TagAdapter? = null
        set(value){
            field = value
            field!!.setOnDataSetChangedListener(this)
            onDataChanged()
        }

    //最多选几个，默认 1 为单选
    var maxSelectCount = 1

    override fun onDataChanged(){
        removeAllViews()
        adapter?.let {
            for (i in 0 until it.getItemCount()) {
                val view = it.createView(LayoutInflater.from(context), this, i)
                it.bindView(view,i)
                addView(view)
                bindViewMethod(i,view)

                //创建时重置
                if (view.isSelected) {
                    it.onItemSelected(view, i)
                } else {
                    it.onItemUnSelected(view,i)
                }
            }

        }

    }

    private fun bindViewMethod(position: Int, view: View) {
        view.setOnClickListener { it ->
            //此处消费了 view 的点击，如果在外部还想用点击实现其他的事情，提供了 onItemViewClick 方法
            adapter!!.onItemViewClick(it,position)

            if (maxSelectCount <= 0) {
                return@setOnClickListener
            }

            //之前 view 没有选中
            if (!view.isSelected) {
                Log.d(TAG, "if ${view.isSelected}")
                //达到最大值
                if (getSelectViewCount() >= maxSelectCount) {

                    //单选
                    if (getSelectViewCount() == 1) {
                        val selectView = getSelectView()
                        selectView?.let {selectView ->
                            selectView.isSelected = false
                            adapter!!.onItemUnSelected(selectView,getPositionForChild(selectView))
                            Log.d(TAG,"单选 ${getPositionForChild(selectView)}")
                        }
                    } else {
                        //多选
                        adapter!!.tipForSelectMax(view,maxSelectCount)
                        return@setOnClickListener
                    }
                }
            }


//            view.isSelected = !view.isSelected

            if (view.isSelected) {
                view.isSelected = false
                adapter?.onItemUnSelected(view,position)
            } else {
                view.isSelected = true
                adapter?.onItemSelected(view,position)
            }
//            Log.d(TAG,"end${view.isSelected.toString()}")
        }
    }

    private fun getPositionForChild(selectView: View): Int {
        for (i in 0 until childCount){
            val view = getChildAt(i)
            return if (selectView == view) {
                i
            }else{
                -1
            }
        }
        return -1
    }

    private fun getSelectView(): View? {
        var result = 0
        for (i in 0 until childCount){
            val view = getChildAt(i)
            if (view.isSelected) {
                return view
            }
        }
        return null
    }

    private fun getSelectViewCount(): Int {
        var result = 0
        for (i in 0 until childCount){
            val view = getChildAt(i)
            if (view.isSelected) {
                result++
            }
        }
        return result
    }

    fun getSelectedItemPositionList(): List<Int> {
        val selectList = mutableListOf<Int>()
        for (i in 0 until childCount){
            val view = getChildAt(i)
            if (view.isSelected) {
                selectList.add(i)
            }
        }
        return selectList
    }
}