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
            "1回目".print()
            emit(1)
            delay(1000L)
            "2回目".print()
            emit(2)
        }.map {
            "map が呼ばれました。 ${it}".print()
            it * 2
        }


        // 1->2  1->2　の順番で呼ばれる
        //cold streamなのでcollectした時点で購買が始まるため
//        runBlocking {
//            flow.collect {
//                "collect1: $it".print()
//            }
//            flow.collect {
//                "collect2: $it".print()
//            }
//        }

        //1,1,2,2の順番で呼ばれる
        //cold streamなのでどちらも呼ばれる
        runBlocking {
            launch {
                flow.collect {
                    "collect1: $it".print()
                }
            }
            launch {
                flow.collect {
                    "collect2: $it".print()
                }
            }
        }
    }
}