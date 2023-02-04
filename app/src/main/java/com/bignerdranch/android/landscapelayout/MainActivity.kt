package com.bignerdranch.android.landscapelayout

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var seekBar: SeekBar
    private lateinit var seekBarLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seek_bar)
        seekBarLabel = findViewById(R.id.seek_bar_label)

        val initialProgress = seekBar.progress + 1 // to set seekbar range from 1 instead of default 0
        updateLabel(initialProgress)

        // TODO add listener to update label as seekbar changes
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            // when user drags the seekBar
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateLabel(progress)
            }

            // when user starts dragging
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            // when user stops dragging
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateLabel(progress: Int) {
        seekBarLabel.text = getString(R.string.seekbar_value_message, progress)
    }
}
