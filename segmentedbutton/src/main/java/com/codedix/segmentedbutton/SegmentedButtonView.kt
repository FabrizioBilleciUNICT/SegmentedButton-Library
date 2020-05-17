package com.codedix.segmentedbutton

import android.content.Context
import android.database.DataSetObserver
import android.util.AttributeSet
import android.widget.Adapter
import android.widget.LinearLayout

open class SegmentedButtonView : LinearLayout {

    private var adapter: Adapter? = null
    private val observer: DataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            refreshViewsFromAdapter()
        }

        override fun onInvalidated() {
            removeAllViews()
        }
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle)

    fun getAdapter(): Adapter? {
        return adapter
    }

    fun setAdapter(adapter: Adapter?) {
        if (this.adapter != null) {
            this.adapter!!.unregisterDataSetObserver(observer)
        }
        this.adapter = adapter
        if (this.adapter != null) {
            this.adapter!!.registerDataSetObserver(observer)
        }
        initViewsFromAdapter()
    }

    private fun initViewsFromAdapter() {
        removeAllViews()
        if (adapter != null) {
            weightSum = adapter!!.count.toFloat()
            System.err.println(adapter!!.count)
            for (i in 0 until adapter!!.count) {
                addView(adapter!!.getView(i, null, this), i)
            }
        }
    }

    protected fun refreshViewsFromAdapter() {
        initViewsFromAdapter()
    }
}