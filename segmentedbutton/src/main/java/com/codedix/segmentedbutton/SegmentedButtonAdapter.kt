package com.codedix.segmentedbutton

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*

class SegmentedButtonAdapter(private val activity: Activity, private val list: ArrayList<String>, selectedItem: Int) :
    ArrayAdapter<String?>(activity, 0) {

    var onItemSelected: ((Int) -> Unit)? = null

    private val START = 0
    private val CENTER = 1
    private val END = 2

    private val bgRes = arrayOf(
        intArrayOf(
            R.drawable.text_background_selected_sx,
            R.drawable.text_background_unselected_sx
        ),
        intArrayOf(
            R.drawable.text_background_selected_cx,
            R.drawable.text_background_unselected_cx
        ),
        intArrayOf(
            R.drawable.text_background_selected_dx,
            R.drawable.text_background_unselected_dx
        )
    )
    private val txColorRes =
        intArrayOf(
            android.R.color.white,
            R.color.colorPrimary
        )

    private var selectedItem = 0
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = activity.layoutInflater
        @SuppressLint("ViewHolder") val rowView = inflater.inflate(R.layout.item, null, true)
        val textView = rowView.findViewById<TextView>(R.id.text)
        textView.text = list[position]

        textView.setBackgroundResource(bgRes[getLayoutPosition(position)][if (position == selectedItem) 0 else 1])
        textView.setTextColor(activity.getColor(txColorRes[if (position == selectedItem) 0 else 1]))

        textView.setOnClickListener {
            selectedItem = position
            onItemSelected?.invoke(position)
            notifyDataSetChanged()
        }

        return rowView
    }

    private fun getLayoutPosition(position: Int): Int {
        return if (position == 0) START else if (position >= 1 && position == list.size - 1) END else CENTER
    }

    init {
        this.selectedItem = selectedItem
    }
}