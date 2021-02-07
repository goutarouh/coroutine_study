package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flow = flow {
            repeat(10) {
                emit(it)
                delay(1000L)
            }
        }.map { it * 424 }

        runBlocking {
            flow.collect {
                it.print()
            }
        }
    }
}