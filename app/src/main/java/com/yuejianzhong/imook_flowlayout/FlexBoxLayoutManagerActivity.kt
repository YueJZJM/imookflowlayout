package com.yuejianzhong.imook_flowlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import com.google.android.flexbox.FlexboxLayoutManager
import com.yuejianzhong.imook_flowlayout.adapter.FlexBoxLayoutManagerAdapter
import kotlinx.android.synthetic.main.activity_flex_box_layout_manager.*

class FlexBoxLayoutManagerActivity : AppCompatActivity() {

    private val mData = ArrayList<String>()

    private lateinit var mAdapter: FlexBoxLayoutManagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flex_box_layout_manager)

        mData.clear()
        for (i in 0 until 400) {
            mData.add("recycler${i}" )
        }
        recycler.layoutManager = FlexboxLayoutManager(this)
        mAdapter = FlexBoxLayoutManagerAdapter(this,mData)
        recycler.adapter = mAdapter
    }
}
