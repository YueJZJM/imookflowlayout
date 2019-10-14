package com.yuejianzhong.imook_flowlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.yuejianzhong.imook_flowlayout.view.FlowLayout
import kotlinx.android.synthetic.main.activity_flow_layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import kotlin.random.Random

class FlowLayoutActivity : AppCompatActivity() {

    private val mDataList = listOf("android","aaa","bbb","android","aaa","bbb","android","aaa","bbb","android","aaa","bbb")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_layout)
        addTag.onClick {
            addTag()
        }

    }

    fun addTag() {
        val tag = LayoutInflater.from(this).inflate(R.layout.item_tag,flowLayout,false) as TextView
        tag.text = mDataList[Random.nextInt(mDataList.size -1)]
        flowLayout.addView(tag)
    }
}
