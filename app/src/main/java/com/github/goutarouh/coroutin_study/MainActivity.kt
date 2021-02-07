package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            val pair = fetchBoth()
            pair.print()
        }
    }

    suspend fun fetchBoth(): Pair<String, String> {
        return coroutineScope {
            val d1 = async { "データ" }
            val d2 = async { "データ2" }
            d1.await() to d2.await()
        }
    }
}