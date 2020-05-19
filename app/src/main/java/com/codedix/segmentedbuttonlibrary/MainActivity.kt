package com.codedix.segmentedbuttonlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.codedix.segmentedbutton.SegmentedButtonAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SegmentedButtonAdapter(
            /* activity: */this,
            /* item list: */arrayListOf("My First Item", "My Item 1", "My Item 2"),
            /* selected item index: */0,
            /* fixed items width: */true)
        radioView.setAdapter(adapter)
        System.err.println(radioView.weightSum)
        adapter.onItemSelected = { index ->
            Toast.makeText(this, "Selected item: $index", Toast.LENGTH_SHORT).show()
        }
    }
}
