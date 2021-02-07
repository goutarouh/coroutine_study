package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scope = CoroutineScope(EmptyCoroutineContext)
        val flow = flow {
            "emit1回目".print()
            emit(1)
        }.map {
            "mapです".print()
            it + 1
        }

        //shareInを使用してHotStreamに変える
        //emitとmapは一回しか出力されなくなる。
        val sharedFlow = flow.shareIn(scope, SharingStarted.Eagerly)
        scope.launch {
            sharedFlow.collect {
                "collect: $it".print()
            }
        }
        scope.launch {
            sharedFlow.collect {
                "collect: $it".print()
            }
        }

        Thread.sleep(1000L)
    }
}