package com.bignerdranch.android.landscapelayout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_SQUARE_SIZE = "com.bignerdranch.android.landscapelayout.tap_the_square.SQUARE_SIZE"

class MainActivity : AppCompatActivity() {

    private lateinit var seekBar: SeekBar
    private lateinit var seekBarLabel: TextView
    private lateinit var showSquareButton: Button

    private val squareResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleSquareResult(result)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seek_bar)
        seekBarLabel = findViewById(R.id.seek_bar_label)
        showSquareButton = findViewById(R.id.show_square_button)

        val initialProgress =
            seekBar.progress + 1 // to set seekbar range from 1 instead of default 0
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

        showSquareButton.setOnClickListener {
            showSquare()
        }
    }

    private fun updateLabel(progress: Int) {
        seekBarLabel.text = getString(R.string.seekbar_value_message, progress)
    }

    private fun showSquare() {
        // launch the SquareActivity
        val showSquareIntent = Intent(this, SquareActivity::class.java)

        // Tell the SquareActivity how large the square should be
        // based on the progress of the Seekbar.
        showSquareIntent.putExtra(EXTRA_SQUARE_SIZE, seekBar.progress)
        // startActivity(showSquareIntent) // starts the SquareActivity but there is no way to receive data back from SquareActivity
        squareResultLauncher.launch(showSquareIntent)
    }
    private fun handleSquareResult(result: ActivityResult) {
        // TODO display result to user
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            // if intent is null, tapped is false because of Elvis operator false ( second false)
            // if EXTRA... doesn't have any value, then is set to false (first false)
            val tapped = intent?.getBooleanExtra(EXTRA_TAPPED_INSIDE_SQUARE, false) ?: false // non nullable- used elvis operator
            val message = if (tapped) {
                getString(R.string.tapped_square)
            } else {
                getString(R.string.missed_square)
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, getString(R.string.did_not_try), Toast.LENGTH_SHORT).show()
        }
    }
}
