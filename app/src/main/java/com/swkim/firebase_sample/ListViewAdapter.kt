package com.swkim.firebase_sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(val List : MutableList<Model>) : BaseAdapter() {

    override fun getCount(): Int {
        return List.count()
    }

    override fun getItem(p0: Int): Any {
        return List[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = p1

        if (view == null) {
            view = LayoutInflater.from(p2?.context).inflate(R.layout.listview_item, p2, false)
        }

        val title = view?.findViewById<TextView>(R.id.itemTextId)
        title?.text = List[p0].title

        return view!!
    }

}