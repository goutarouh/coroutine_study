package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flow: Flow<Int> = flow {
            repeat(10) {
                emit(it)
                delay(1000L)
            }
        }

        //collectが2秒に一回なので
        //2秒に一回しか購買しなくなる。
        runBlocking {
//            flow
//                    .onEach {
//                        "onEach: $it".print()
//                    }
//                    .collect {
//                        "collect: $it".print()
//                        delay(2000L)
//                    }

            //bufferを使用するとため込みができるため
            //onEachは出力される
            flow
                    .onEach {
                        "onEach: $it".print()
                    }
                    .buffer(10)
                    .collect {
                        "collect: $it".print()
                        delay(2000L)
                    }
        }
    }
}