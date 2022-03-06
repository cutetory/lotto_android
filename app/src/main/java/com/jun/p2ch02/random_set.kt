package com.jun.p2ch02

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class random_set : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val random = Random()
        val numberSet = mutableSetOf<Int>()
        while(numberSet.size < 6){
            val randomNumber = random.nextInt(45) + 1
            numberSet.add(randomNumber)
        }
        println(numberSet)
    }
}