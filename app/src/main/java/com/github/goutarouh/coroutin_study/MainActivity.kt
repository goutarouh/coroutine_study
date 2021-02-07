package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flow1 = flow {
            emit(1)
            delay(50L)
            emit(2)
        }
        val flow2 = flow {
            emit(3)
            delay(50L)
            emit(4)
        }

        val combined = combine(flow1, flow2) {t1, t2 ->
            t1 + t2
        }

        //flowの合成
        //どちらかがemitされたときに取得する
        runBlocking {
            combined.collect {
                it.print()
            }
        }


        //launchInでネストを減らす
        val scope = CoroutineScope(EmptyCoroutineContext)
        flow1.onEach {
            it.print()
        }.launchIn(scope)
    }
}