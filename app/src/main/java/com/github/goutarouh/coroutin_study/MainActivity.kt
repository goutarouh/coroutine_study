package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runBlocking {
            val deferred1 = async {
                delay(1000L)
                3
            }

            val deferred2 = async {
                delay(1000L)
                2
            }

            val sum = deferred1.await() + deferred2.await()
            sum.print()
        }
    }
}