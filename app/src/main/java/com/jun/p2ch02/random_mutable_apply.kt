package com.jun.p2ch02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class random_mutable_apply : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val random = Random()
        //apply는 주로 초기화 할 때 쓴다!
        val list =mutableListOf<Int>().apply{
            for(i in 1..45){
                this.add(i)
            }
        }
        list.shuffle()
        println(list.subList(0, 6))
    }
}