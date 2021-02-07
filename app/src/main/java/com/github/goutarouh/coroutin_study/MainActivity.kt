package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            "catchしたよ: ${throwable}".print()
        }

        //val context = Job() + exceptionHandler
        val context = SupervisorJob() + exceptionHandler

        val scope = CoroutineScope(context)

        scope.launch {
            delay(500L)
            throw Exception("error")
        }

        scope.launch {
            delay(1000L)
            "completed2".print()
        }
        Thread.sleep(2000L)
    }
}