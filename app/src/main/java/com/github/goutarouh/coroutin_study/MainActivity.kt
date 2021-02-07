package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            val job1 = launch {
                delay(100L)
                "completed: job1".print()
            }
            val job2 = launch {
                delay(100L)
                "completed: job2".print()
            }
            job1.join()
            job2.join()
            "completed: all".print()
        }

        runBlocking {
            val job1 = launch {
                "start: job1".print()
                delay(100L)
                "completed: job1".print()
            }
            val job2 = launch {
                "start: job2".print()
                delay(100L)
                "completed: job2".print()
            }

            delay(50L)
            job1.cancel()
        }
    }
}