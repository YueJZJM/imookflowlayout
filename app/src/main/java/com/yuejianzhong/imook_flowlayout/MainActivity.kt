package com.yuejianzhong.imook_flowlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flow_activity.onClick {
            startActivity<FlowLayoutActivity>()
        }
        flew_activity.onClick {
            startActivity<FlexBoxLayoutActivity>()
        }

        flew_recycler_activity.onClick {
            startActivity<FlexBoxLayoutManagerActivity>()
        }

        tag_flow_activity.onClick {
            startActivity<TagFlowActivity>()
        }
    }
}
