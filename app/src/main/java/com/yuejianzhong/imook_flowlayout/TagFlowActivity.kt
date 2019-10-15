package com.yuejianzhong.imook_flowlayout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yuejianzhong.imook_flowlayout.view.TagFlowLayout
import kotlinx.android.synthetic.main.activity_tag_flow.*
import kotlinx.android.synthetic.main.item_select_tag.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.textColor
import com.yuejianzhong.imook_flowlayout.view.TagAdapter as TagAdapter

class TagFlowActivity : AppCompatActivity() {

    private val mDataList = mutableListOf("android","aaacc","bbb","android","aaddsaa","bbbdwew","android","aafafa","bbwefsab","android","aa11111a","bbb")
    private lateinit var mAdapter: TagAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_flow)
        tag_flow_layout.maxSelectCount = 3
        tag_flow_layout.adapter = object :TagAdapter(){

            override fun onItemSelected(view: View, position: Int) {
                super.onItemSelected(view, position)
                val tvTag = view.findViewById<TextView>(R.id.select_tv)
                tvTag.textColor = Color.YELLOW
            }

            override fun onItemUnSelected(view: View, position: Int) {
                super.onItemUnSelected(view, position)
                val tvTag = view.findViewById<TextView>(R.id.select_tv)
                tvTag.textColor = Color.BLACK
            }

            override fun onItemViewClick(view: View, position: Int){

                longToast("位置 ${tag_flow_layout.getSelectedItemPositionList().toString()}")

            }

            override fun tipForSelectMax(view: View, maxSelectCount: Int){
                super.tipForSelectMax(view, maxSelectCount)
                longToast("最多选择${maxSelectCount}个").show()
            }


            override fun getItemCount(): Int {
                return mDataList.size
            }

            override fun createView(
                inflater: LayoutInflater,
                parent: ViewGroup,
                position: Int
            ): View {
                return inflater.inflate(R.layout.item_select_tag,parent,false)
            }

            override fun bindView(view: View, position: Int) {
                val textView = view.findViewById(R.id.select_tv) as TextView
                textView.text = mDataList[position]
            }

        }

//        mAdapter

        change_Data.onClick {
            mDataList.clear()
            mDataList.addAll (0,mutableListOf("ppp","feisfe","rewjwk","q","21","2u932jd3io","3423fdsfe","2qdsade","24")
            )

            mDataList.addAll(arrayListOf("adsa"))
            tag_flow_layout.maxSelectCount = 1
            this@TagFlowActivity.tag_flow_layout.adapter?.notifyDataSetChanged()
        }
    }
}
