package com.github.goutarouh.coroutin_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            val context1 = Job()
            val scope1 = CoroutineScope(context1)

            //5000で止まらない
            scope1.launch {
                var i = 0
                while (i <= 1_000_000) {
                    if (i % 1_000 == 0) {
                        i.print()
                    }
                    if (i == 500_000) {
                        scope1.cancel()
                    }
                    i++
                }
            }

            //isActiveで自身がキャンセルされていないか確認する
            //(suspend関数は自信がキャンセルされているか確認する)
            //5000で止まる
            runBlocking {
                val context2 = Job()
                val scope2 = CoroutineScope(context2)
                scope2.launch {
                    var i = 0
                    while (i <= 1_000_00 && isActive) {
                        if (i % 1_000 == 0) {
                            i.print()
                        }
                        if (i == 500_00) {
                            scope2.cancel()
                        }
                        i++

                        //キャンセルされていた場合
                        //CancellationExceptionを出す
                        //ensureActive()
                    }
                }
            }
        }



    }
}