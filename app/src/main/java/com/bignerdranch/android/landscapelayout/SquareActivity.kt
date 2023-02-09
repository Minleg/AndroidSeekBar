package com.bignerdranch.android.landscapelayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_TAPPED_INSIDE_SQUARE =
    "com.bignerdranch.android.landscapelayout.TAPPED_INSIDE_SQUARE"

class SquareActivity : AppCompatActivity() {

    private lateinit var squareImage: ImageView
    private lateinit var container: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_square)

        squareImage = findViewById(R.id.square)
        container = findViewById(R.id.container)

        var squareSize = intent.getIntExtra(EXTRA_SQUARE_SIZE, 100)
        if (squareSize == 0) {
            squareSize = 1
        }

        squareImage.layoutParams.width = squareSize
        squareImage.layoutParams.height = squareSize

        squareImage.setOnClickListener {
            squareTapped(true)
        }

        container.setOnClickListener {
            squareTapped(false)
        }
    }

    private fun squareTapped(didTapSqaure: Boolean) {
        /*
        // send a message to main activity
        // end square activity
        // Toast.makeText(this, "square tapped $didTapSqaure", Toast.LENGTH_SHORT).show()
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_TAPPED_INSIDE_SQUARE, didTapSqaure)
        // if user clicked back button before doing the required task in the activity,
        // it would be RESULT_CANCEL
        setResult(RESULT_OK, resultIntent) // RESULT_OK indicates that everything worked as intented and user tapped on somewhere
        finish() // finishing the SquareActivity and we return back to calling Activity which is the MainActivity
         */

        Intent().apply {
            putExtra(EXTRA_TAPPED_INSIDE_SQUARE, didTapSqaure)
            setResult(RESULT_OK,this)
            finish()
        }
    }
}
