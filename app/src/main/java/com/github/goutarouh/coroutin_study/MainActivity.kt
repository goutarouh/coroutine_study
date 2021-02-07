package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runBlocking {
            val job = launch {
                try {
                    "start".print()
                    delay(100L)
                    "end".print()
                } catch (e: CancellationException) {
                    "catch: $e".print()
                } finally {
                    "finally".print()
                }
            }
            delay(50L)
            job.cancel()
        }
    }
}