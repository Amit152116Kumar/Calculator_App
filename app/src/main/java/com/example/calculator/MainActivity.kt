package com.example.calculator

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

const val PI: Double= 3.14159
var output_result: String=""
lateinit var binding:ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var vibrator:Vibrator
//    TO STORE ALL OPERATIONS AND SHOW ON EDIT TEXT
    private var sum=""
//    TO STORE CLICKED BUTTON INPUT
    private var inputText=""
//    FOR THE OPERATION BUTTON
    private var pushButton=""
//    TO STORE ONE DIGIT
    private var sumNumber=""
//    FOR MAX LIMIT OF ONE DIGIT IN EDIT TEXT
    private var countNumber:Long=0
//    TO NOT ADD TWO OPERATIONS CONTINUOUSLY IN CALCULATOR
    private var flag=0
//    FOR OPERATION CLASS INSTANCE
    private lateinit var operation:Operations



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vibrator= getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        operation=Operations(binding)

        binding.angleDegree.setOnClickListener {
            vibrator.vibrate(30)
            if (binding.angleDegree.text.toString()==getString(R.string.deg)){
                binding.angleDegree.setText(R.string.rad)
            }
            else if (binding.angleDegree.text.toString()==getString(R.string.rad)){
                binding.angleDegree.setText(R.string.deg)
            }
        }


    }

    fun plusOnclickListener(view: View) {
        vibrator.vibrate(30)
        pushButton="+"
        flag=1
        countNumber=0
        sumNumber=""
        if (sum!=""){
            output_result=binding.outputText.text.toString()
            inputText=binding.plus.text as String
            sum+=inputText
            binding.editText.setText(sum)
            Log.d("TAG", "plusOnclickListener: ${output_result}")
        }
    }

    fun nineOnclickListener(view: View) {
        vibrator.vibrate(30)
        operation.limit_checker()
        flag=0
        if (countNumber<=18) {

            inputText = binding.nine.text as String
            sumNumber+=inputText
            sum += inputText
            binding.editText.setText(sum)
            if (pushButton == "+")   { binding.outputText.text=operation.addition(sumNumber) }

            else if (pushButton=="-")     { binding.outputText.text= operation.subtraction(sumNumber) }

            else if (pushButton=="*")     { binding.outputText.text= operation.multiplication(sumNumber) }

            else if (pushButton=="/")       { binding.outputText.text= operation.division(sumNumber) }

            else {
                Log.d("TAG", "nineOnclickListener: else")
                binding.outputText.text = sum
            }
            countNumber++
        }
    }

    fun dotOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag=0
        if (!sumNumber.contains(".")){
            operation.limit_checker()
            if (countNumber<=18) {
                inputText = binding.dot.text as String
                sumNumber+=inputText
                sum += inputText
                binding.editText.setText(sum)
                if (pushButton == "+") {
                    operation.addition(sumNumber)
                } else {
                    Log.d("TAG", "dotOnclickListener: else")
                    binding.outputText.text = sum
                }
                countNumber++
            }
        }
    }

    fun resetOnclickListener(view: View) {
        vibrator.vibrate(30)
        binding.editText.textSize = 50F
        binding.outputText.textSize = 50F
        countNumber=0
        flag=0
        output_result=""
        sum=""
        inputText=""
        pushButton=""
        binding.editText.setText("")
        binding.outputText.text = "0"
    }

    fun zeroOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag=0
        if (sumNumber.isNotEmpty()){
            operation.limit_checker()
            if (countNumber<=18) {
                inputText = binding.zero.text as String
                sumNumber+=inputText
                sum += inputText
                binding.editText.setText(sum)
                if (pushButton == "+") {
                    operation.addition(sumNumber)
                } else {
                    Log.d("TAG", "zeroOnclickListener: else")
                    binding.outputText.text = sum
                }
                countNumber++
            }

        }


    }

    fun deleteOnclickListener(view: View) {
        if (sum==sumNumber){
            sum.removeRange(sum.length - 1, sum.length)
            binding.editText.setText(sum)
            sumNumber.removeRange(sumNumber.length - 1, sumNumber.length)
            binding.outputText.text=sumNumber
        }
        else{

        }
    }

    fun oneOnclickListener(view: View) {}
    fun twoOnclickListener(view: View) {}
    fun threeOnclickListener(view: View) {}
    fun fourOnclickListener(view: View) {}
    fun fiveOnclickListener(view: View) {}
    fun sixOnclickListener(view: View) {}
    fun sevenOnclickListener(view: View) {}
    fun eightOnclickListener(view: View) {}


}