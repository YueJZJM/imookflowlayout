package com.yuejianzhong.imook_flowlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_flex_box_layout.*
import kotlinx.android.synthetic.main.activity_flow_layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import kotlin.random.Random

class FlexBoxLayoutActivity : AppCompatActivity() {

    private val mDataList = listOf("android","aaa","bbb","android","aaa","bbb","android","aaa","bbb","android","aaa","bbb")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flex_box_layout)
        add_flex_box_tag.onClick {
            addTag()
        }
    }

    fun addTag() {
        val tag = LayoutInflater.from(this).inflate(R.layout.item_tag,flowLayout,false) as TextView
        tag.text = mDataList[Random.nextInt(mDataList.size -1)]
        flex_box.addView(tag)
    }
}
