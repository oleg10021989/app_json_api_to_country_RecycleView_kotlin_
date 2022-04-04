package com.example.ep.myapplication.Activitys.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ep.myapplication.Activitys.Model.State
import com.example.ep.myapplication.R
import java.util.*

class StateAdapter(context: Context?, values: ArrayList<State>?) : ArrayAdapter<State?>(
    context!!, R.layout.rowlayout, values!! as List<State?>
) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View // setting up the objects from the array on the listview
    {
        val theInflater = LayoutInflater.from(context)
        @SuppressLint("ViewHolder") val theView =
            theInflater.inflate(R.layout.rowlayout, parent, false)
        val state = getItem(position)
        val textViewName = theView.findViewById<View>(R.id.textView1) as TextView
        val textViewnativeName = theView.findViewById<View>(R.id.textView2) as TextView
        textViewName.text = state!!.name
        textViewnativeName.text = state.nativeName


        return theView
    }


}