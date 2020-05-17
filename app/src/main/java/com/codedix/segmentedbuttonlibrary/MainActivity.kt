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

        val adapter = SegmentedButtonAdapter(this, arrayListOf("My Item 0", "My Item 1", "My Item 2"), 0)
        radioView.setAdapter(adapter)
        adapter.onItemSelected = { index ->
            Toast.makeText(this, "Selected item: $index", Toast.LENGTH_SHORT).show()
        }
    }
}
