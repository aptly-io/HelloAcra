package io.aptly.helloacra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private fun causeException1() {
        throw IllegalArgumentException("Report me to Acrarium!")
    }

    private fun causeException2() {
        throw IllegalStateException("Report me also to Acrarium!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonException1).setOnClickListener { causeException1() }
        findViewById<Button>(R.id.buttonException2).setOnClickListener { causeException2() }
    }
}