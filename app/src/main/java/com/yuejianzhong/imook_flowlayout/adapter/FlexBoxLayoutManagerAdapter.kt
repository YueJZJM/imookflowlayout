package com.yuejianzhong.imook_flowlayout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yuejianzhong.imook_flowlayout.R

class FlexBoxLayoutManagerAdapter(val mContext: Context,val mDatas:List<String>) : RecyclerView.Adapter<FlexBoxLayoutManagerAdapter.ViewHolder>() {

    private val mLayoutInflater by lazy {
        LayoutInflater.from(mContext)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_tag,parent,false))
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemText.text = mDatas[position]
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val itemText:TextView = itemView.findViewById(R.id.tv)
    }
}