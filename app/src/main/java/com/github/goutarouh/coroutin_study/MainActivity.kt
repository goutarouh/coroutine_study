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

        val scope = CoroutineScope(EmptyCoroutineContext)
        scope.launch {
            1.print()
            delay(1000L)
            2.print()
        }
        scope.launch {
            3.print()
            delay(1000L)
            4.print()
        }
        scope.cancel()
    }
}