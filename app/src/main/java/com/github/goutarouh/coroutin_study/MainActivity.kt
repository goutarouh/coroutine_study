package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scope = CoroutineScope(EmptyCoroutineContext)

        //MutableSharedFlowは値を外部から送信することができる。
        //外部と共有ってことでshareかな?
        val mutableSharedFlow = MutableSharedFlow<Int>()


        scope.launch {
            mutableSharedFlow.collect {
                "collect: $it".print()
            }
        }

        //cold streamなので
        //こちらも同時に流れてきます。
        scope.launch {
            mutableSharedFlow.collect{
                    "collect: $it".print()
            }
        }

        //emitした時点で購買登録されていたもののみに発射
        scope.launch {
            mutableSharedFlow.emit(1)
            delay(1000L)
            mutableSharedFlow.emit(2)
        }

        runBlocking {
            delay(500L)
        }

        //2だけ取得する
        scope.launch {
            mutableSharedFlow.collect {
                "collect! $it".print()
            }
        }


        Thread.sleep(2000L)
    }
}