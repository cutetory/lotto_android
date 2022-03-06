package com.jun.p2ch02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val clearButton: Button by lazy {
        findViewById<Button>(R.id.ClearButton)
    }

    private val addButton: Button by lazy {
        findViewById<Button>(R.id.addButton)
    }

    private val runButton: Button by lazy {
        findViewById<Button>(R.id.runButton)
    }

    private val numberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.NumberPicker)
    }

    private val numberTextViewList : List<TextView> by lazy{
        listOf<TextView>(
            findViewById<TextView>(R.id.resultnum1),
            findViewById<TextView>(R.id.resultnum2),
            findViewById<TextView>(R.id.resultnum3),
            findViewById<TextView>(R.id.resultnum4),
            findViewById<TextView>(R.id.resultnum5),
            findViewById<TextView>(R.id.resultnum6)
        )
    }

    private var didRun = false
    //set이 중복을 피하는것
    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }

    /* 여기서부터는 함수*/
//람다식 매개변수 화살표
    private fun initRunButton() {
        runButton.setOnClickListener {
            val list = getRandomNumber()
            didRun = true

            list.forEachIndexed{index, number ->
                val textView = numberTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number, textView)
            }
            Log.d("MainActivity", list.toString())
        }
    }//End of initRunButton()

    private fun initAddButton(){
        addButton.setOnClickListener{
            if (didRun){
                Toast.makeText(this, "초기화 후에 시도해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(pickNumberSet.size >= 6){
                Toast.makeText(this, "번호는 6개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(pickNumberSet.contains(numberPicker.value)){
                Toast.makeText(this,"이미 선택한 번호입니다.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //kotlin이므로 setbackground이런건 생략 가능
            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            //drawable을 가져오는 것이므로 Context에서 가져오는 것
/*            when(numberPicker.value){
                in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_yellow)
                in 11..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
                in 21..30 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
                in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_gray)
                else -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_green)

            }*/
            setNumberBackground(numberPicker.value, textView)
            pickNumberSet.add(numberPicker.value)

        }
    }// End of initAddButton()

    //중복되는 코드 없애기 위함.
    private fun setNumberBackground(number:Int, textView: TextView){
        when(number){
            in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_yellow)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_gray)
            else -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_green)
        }
    }

    private fun initClearButton(){
        clearButton.setOnClickListener{
            pickNumberSet.clear()
            numberTextViewList.forEach{
                it.isVisible = false
            }
            didRun = false
        }
    }// End of initClearButton()

    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for (i in 1..45) {
                if(pickNumberSet.contains(i)){
                    continue
                }
                this.add(i)
            }
        }
        //마구잡이로 섞기
        numberList.shuffle()
        val newList = pickNumberSet.toList() + numberList.subList(0, 6 - pickNumberSet.size)
        return newList.sorted()
    }//End of getRandomNumber()
}