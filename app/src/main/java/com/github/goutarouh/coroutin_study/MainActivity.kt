package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val context = EmptyCoroutineContext
        val context = Job()
        val scope = CoroutineScope(context)
        "job ${context[Job]}".print()
        scope.launch {
            "scope.job ${scope.coroutineContext[Job]}".print()
            "start1".print()
            delay(1000L)
            "start2".print()
        }

        context.cancel()
        Thread.sleep(2000L)

    }
}