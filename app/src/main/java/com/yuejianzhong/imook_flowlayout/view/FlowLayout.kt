package com.yuejianzhong.imook_flowlayout.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.yuejianzhong.imook_flowlayout.R
import kotlin.math.max
import kotlin.math.min

class FlowLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    ViewGroup(context, attrs) {

    //所有的 view，二维数组
    private val mAllView = ArrayList<List<View>>()

    //每一行的高度
    private val mLineHeight = ArrayList<Int>()

    val TAG = "FlowLayout"

    private var mMaxLines:Int = 0

    /**
     * 1.宽度：是确定的，因为子 view 需要根据宽度确认是否换行
     * 2.高度：wrapcontent，exactly，unspe
     */

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout)
        mMaxLines= typeArray.getInt(R.styleable.FlowLayout_maxLines, Int.MAX_VALUE)
        typeArray.recycle()
        Log.d(TAG,"mMaxLines: $mMaxLines")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        //onMeasure 会执行多次，所以需要 clean
        mAllView.clear()
        mLineHeight.clear()

        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)

        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)

        var lineWidth = 0
        var height = 0
        var lineHeight = 0

        val cCount = childCount

        Log.e(TAG, sizeWidth.toString())


        //一行所有的 view
        var lineViews = ArrayList<View>()

        //拿到当前所有 child 的高度，设置给 viewgroup
        for (i in 0 until cCount) {
            val child = getChildAt(i)

            if (child.visibility == View.GONE) {
                continue
            }

            //child 也要确定宽高
            measureChild(child,widthMeasureSpec,heightMeasureSpec)
            val lp = child.layoutParams as MarginLayoutParams

            val cWidth = child.measuredWidth + lp.leftMargin + lp.rightMargin
            val cHeight = child.measuredHeight + lp.topMargin+ lp.bottomMargin

            Log.d(TAG,"cWidth $cWidth")

            //如果当前宽度 > viewgroup 宽，换行
            if (lineWidth + cWidth > sizeWidth - (paddingLeft+paddingRight)) {

                height += lineHeight
                Log.d(TAG,"换行 $height,$lineHeight")

                mLineHeight.add(lineHeight)

                mAllView.add(lineViews)
                lineViews = ArrayList()
                lineViews.add(child)

                //重置
                lineWidth = cWidth
                lineHeight = cHeight



            } else {
                //未换行
                lineWidth += cWidth
                lineHeight = max(lineHeight,cHeight)

                lineViews.add(child)
            }

            //最后一行一定没有换行，需要把它加上
            if (i == cCount - 1) {
                height += lineHeight

                mLineHeight.add(lineHeight)
                mAllView.add(lineViews)
            }
        }

        // maxLines 校正
        if (mMaxLines < mLineHeight.size) {
            height = getMaxLinesHeight()
        }

        if (modeHeight == MeasureSpec.EXACTLY) {
            Log.d(TAG, "EXACTLY,sizeHeight: $sizeHeight")
            height = sizeHeight
        }
        else if (modeHeight == MeasureSpec.AT_MOST){
            height = min(sizeHeight,height)
            height += paddingTop + paddingBottom
            Log.d(TAG,"AT_MOST,sizeHeight: $sizeHeight,height: $height")
        }else if (modeHeight == MeasureSpec.UNSPECIFIED) {
            height += paddingTop + paddingBottom
        }

        setMeasuredDimension(sizeWidth,height)

        Log.d(TAG,"sizeWidth ${sizeWidth.toString()}")
        Log.d(TAG,"height： ${height.toString()}")

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //摆放 view，需要拿到 lineHeight
        var left = paddingLeft
        var top = paddingTop
        val lineNums = mAllView.size
        for (i in 0 until lineNums) {
            val lineViews = mAllView[i]
            val lineHeight = mLineHeight[i]
            for (j in 0 until lineViews.size) {
                val child = lineViews[j]

                val lp = child.layoutParams as MarginLayoutParams

                val lc = left+lp.leftMargin
                val tc = top + lp.topMargin
                val rc = child.measuredWidth + lc
                val bc = child.measuredHeight + tc

                child.layout(lc, tc, rc, bc)

                //移动 left
                left += child.measuredWidth + lp.leftMargin + lp.rightMargin

            }
            left = paddingLeft
            top += lineHeight

        }
    }

    fun getMaxLinesHeight(): Int {
        var height = 0
        for (i in 0 until mMaxLines) {
            height += mLineHeight[i]
        }
        return height
    }

    /**
     * 如果 view 是 new 出来的，如果没有设置 layoutParams
     * 这里设置 MarginLayoutParams 为默认的 layoutParams
     */
    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
    }

    /**
     * 布局生成自 view 时调用
     */
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context,attrs)
    }

    /**
     * addView 的情况，如果设置的不是 MarginLayoutParams，内部首先会调用 checkLayoutParams
     * 判断是否为 MarginLayoutParams ，如果不是，则会调用 generateLayoutParams 转化为
     * MarginLayoutParams
     */
    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun checkLayoutParams(p: LayoutParams?): Boolean {
        return p is MarginLayoutParams
    }
}

