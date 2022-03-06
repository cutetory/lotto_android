package com.jun.p2ch02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class random_mutableList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val random = Random()
        val list = mutableListOf<Int>()

        while(list.size < 6){
            val randomNumber = random.nextInt(45) + 1
            //contains부분이 특이함
            if(list.contains(randomNumber)){
                continue
            }
            list.add(randomNumber)
        }
        println(list)
    }
}