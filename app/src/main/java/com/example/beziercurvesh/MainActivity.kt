package com.example.beziercurvesh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.timerTask
import android.os.SystemClock




class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY_COLOR = "key-color"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rallyLine.addDataPoints(getRandomPoints())
        rallyLine.setOnClickListener {
            rallyLine.addDataPoints(getRandomPoints())
        }

        rallyLineE01.setOnClickListener {
            rallyLineE01.addDataPoints(getRandomPoints())
        }

        val handler = Handler()
        val runnable = Runnable {
            while (true) {
                handler.postDelayed({
                    rallyLineE01.addDataPoints(getRandomPoints())
                }, 0)
                SystemClock.sleep(300)
            }
        }
        Thread(runnable).start()
    }

    private fun getRandomPoints(): MutableList<DataPoint> {
        val list = mutableListOf<DataPoint>()
        val range = (0..10)

        (1..15).forEach { _ ->
            list.add(DataPoint(range.random() * 100f))
        }
        return list
    }


}
