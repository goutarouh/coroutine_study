package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            val context = EmptyCoroutineContext
            val scope = CoroutineScope(context)

            //中段関数がない場合同期的に処理が進む可能性もある
            scope.launch {
                var i = 0
                while (i <= 1_000_000) {
                    if (i % 1_000 == 0) {
                        i.print()
                        yield()
                    }
                    i++
                }
            }
            //yieldを入れることで他の実行可能なcoroutineを探してくれる
            scope.launch {
                "hello".print()
            }
        }
    }
}