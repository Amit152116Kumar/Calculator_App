package com.example.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

const val PI: Double = 3.14159
var output_result: String = ""
lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var vibrator: Vibrator

    //    TO STORE ALL OPERATIONS AND SHOW ON EDIT TEXT
    private var sum = ""

    //    TO STORE CLICKED BUTTON INPUT
    private var inputText = ""

    //    FOR THE OPERATION BUTTON
    private var pushButton = ""

    //    TO STORE ONE DIGIT
    private var sumNumber = ""

    //    FOR MAX LIMIT OF ONE DIGIT IN EDIT TEXT
    private var countNumber: Long = 0

    //    TO NOT ADD TWO OPERATIONS CONTINUOUSLY IN CALCULATOR
    private var flag = 1

    //    FOR OPERATION CLASS INSTANCE
    private lateinit var operation: Operations


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        operation = Operations(binding)

        binding.angleDegree.setOnClickListener {
            vibrator.vibrate(30)
            if (binding.angleDegree.text.toString() == getString(R.string.deg)) {
                binding.angleDegree.setText(R.string.rad)
            } else if (binding.angleDegree.text.toString() == getString(R.string.rad)) {
                binding.angleDegree.setText(R.string.deg)
            }
        }


    }

    fun plusOnclickListener(view: View) {
        vibrator.vibrate(30)
        countNumber = 0
        sumNumber = ""
        if (flag == 0) {
            flag = 1
            pushButton = "+"
            if (sum != "") {
                output_result = binding.outputText.text.toString().removePrefix("=")
                inputText = binding.plus.text as String
                sum += inputText
                binding.editText.setText(sum)
                Log.d("TAG", "plusOnclickListener: $output_result")
            }
        }
    }

    fun minusOnclickListener(view: View) {
        vibrator.vibrate(30)
        countNumber = 0
        sumNumber = ""
        if (flag == 0) {
            flag = 1
            pushButton = "-"
            if (sum != "") {
                output_result = binding.outputText.text.toString().removePrefix("=")
                inputText = binding.minus.text as String
                sum += inputText
                binding.editText.setText(sum)
                Log.d("TAG", "minusOnclickListener: $output_result")
            }
        }
    }

    fun multiplyOnclickListener(view: View) {
        vibrator.vibrate(30)
        countNumber = 0
        sumNumber = ""
        if (flag == 0) {
            flag = 1
            pushButton = "*"
            if (sum != "") {
                output_result = binding.outputText.text.toString().removePrefix("=")
                inputText = binding.multiply.text as String
                sum += inputText
                binding.editText.setText(sum)
                Log.d("TAG", "multiplyOnclickListener: $output_result")
            }
        }
    }

    fun divideOnclickListener(view: View) {
        vibrator.vibrate(30)
        countNumber = 0
        sumNumber = ""
        if (flag == 0) {
            flag = 1
            pushButton = "/"
            if (sum != "") {
                output_result = binding.outputText.text.toString().removePrefix("=")
                inputText = binding.divide.text as String
                sum += inputText
                binding.editText.setText(sum)
                Log.d("TAG", "divideOnclickListener: $output_result")
            }
        }

    }


    fun nineOnclickListener(view: View) {
        vibrator.vibrate(30)

        flag = 0
        if (sum.isEmpty()) {
            binding.outputText.setTextColor(Color.GRAY)
            binding.outputText.textSize = 35F
            binding.editText.setTextColor(Color.BLACK)
            binding.editText.textSize = 50F
        }

        if (countNumber <= 18) {
            operation.limit_checker()
            inputText = binding.nine.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton) {
                "+" -> {
                    "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
                }
                "/" -> {
                    "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                }
                else -> {
                    Log.d("TAG", "nineOnclickListener: else")
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }


    fun dotOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sumNumber.isNotEmpty()) {
            if (!sumNumber.contains(".")) {
                operation.limit_checker()
                if (countNumber <= 18) {
                    inputText = binding.dot.text as String
                    sumNumber += inputText
                    sum += inputText
                    binding.editText.setText(sum)
                    countNumber++
                }
            }
        }
    }

    fun resetOnclickListener(view: View) {
        vibrator.vibrate(30)
        binding.editText.textSize = 50F
        binding.outputText.textSize = 50F
        sum = ""
        inputText = ""
        pushButton = ""
        sumNumber = ""
        countNumber = 0
        flag = 1
        output_result = ""
        binding.editText.setText("")
        binding.outputText.text = "0"
        binding.outputText.setTextColor(Color.BLACK)
        binding.editText.setTextColor(Color.BLACK)
    }


    fun zeroOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sum.isNotEmpty()) {
            operation.limit_checker()
            if (countNumber <= 18) {
                inputText = binding.zero.text as String
                sumNumber += inputText
                sum += inputText
                binding.editText.setText(sum)
                when (pushButton) {
                    "+" -> {
                        "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                    }
                    "-" -> {
                        "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                    }
                    "*" -> {
                        "=${operation.multiplication(sumNumber)}".also {
                            binding.outputText.text = it
                        }
                    }
                    "/" -> {
                        "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                    }
                    else -> {
                        Log.d("TAG", "nineOnclickListener: else")
                        "=$sum".also { binding.outputText.text = it }
                    }
                }
                countNumber++
            }

        }


    }

    @SuppressLint("SetTextI18n")
    fun deleteOnclickListener(view: View) {
        if (sum == sumNumber) {
            sum.removeRange(sum.length - 1, sum.length)
            binding.editText.setText(sum)
            sumNumber.removeRange(sumNumber.length - 1, sumNumber.length)
            binding.outputText.text = "=$sumNumber"
        }
    }

    fun oneOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sum.isEmpty()) {
            binding.outputText.setTextColor(Color.GRAY)
            binding.outputText.textSize = 35F
            binding.editText.setTextColor(Color.BLACK)
            binding.editText.textSize = 50F
        }
        if (countNumber <= 18) {
            operation.limit_checker()
            inputText = binding.one.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton) {
                "+" -> {
                    "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
                }
                "/" -> {
                    "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                }
                else -> {
                    Log.d("TAG", "nineOnclickListener: else")
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun twoOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sum.isEmpty()) {
            binding.outputText.setTextColor(Color.GRAY)
            binding.outputText.textSize = 35F
            binding.editText.setTextColor(Color.BLACK)
            binding.editText.textSize = 50F
        }
        if (countNumber <= 18) {
            operation.limit_checker()
            inputText = binding.two.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton) {
                "+" -> {
                    "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
                }
                "/" -> {
                    "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                }
                else -> {
                    Log.d("TAG", "nineOnclickListener: else")
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun threeOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sum.isEmpty()) {
            binding.outputText.setTextColor(Color.GRAY)
            binding.outputText.textSize = 35F
            binding.editText.setTextColor(Color.BLACK)
            binding.editText.textSize = 50F
        }
        if (countNumber <= 18) {
            operation.limit_checker()
            inputText = binding.three.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton) {
                "+" -> {
                    "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
                }
                "/" -> {
                    "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                }
                else -> {
                    Log.d("TAG", "nineOnclickListener: else")
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun fourOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sum.isEmpty()) {
            binding.outputText.setTextColor(Color.GRAY)
            binding.outputText.textSize = 35F
            binding.editText.setTextColor(Color.BLACK)
            binding.editText.textSize = 50F
        }
        if (countNumber <= 18) {
            operation.limit_checker()
            inputText = binding.four.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton) {
                "+" -> {
                    "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
                }
                "/" -> {
                    "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                }
                else -> {
                    Log.d("TAG", "nineOnclickListener: else")
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun fiveOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sum.isEmpty()) {
            binding.outputText.setTextColor(Color.GRAY)
            binding.outputText.textSize = 35F
            binding.editText.setTextColor(Color.BLACK)
            binding.editText.textSize = 50F
        }
        if (countNumber <= 18) {
            operation.limit_checker()
            inputText = binding.five.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton) {
                "+" -> {
                    "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
                }
                "/" -> {
                    "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                }
                else -> {
                    Log.d("TAG", "nineOnclickListener: else")
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun sixOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sum.isEmpty()) {
            binding.outputText.setTextColor(Color.GRAY)
            binding.outputText.textSize = 35F
            binding.editText.setTextColor(Color.BLACK)
            binding.editText.textSize = 50F
        }
        if (countNumber <= 18) {
            operation.limit_checker()
            inputText = binding.six.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton) {
                "+" -> {
                    "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
                }
                "/" -> {
                    "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                }
                else -> {
                    Log.d("TAG", "nineOnclickListener: else")
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun sevenOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sum.isEmpty()) {
            binding.outputText.setTextColor(Color.GRAY)
            binding.outputText.textSize = 35F
            binding.editText.setTextColor(Color.BLACK)
            binding.editText.textSize = 50F
        }
        if (countNumber <= 18) {
            operation.limit_checker()
            inputText = binding.seven.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton) {
                "+" -> {
                    "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
                }
                "/" -> {
                    "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                }
                else -> {
                    Log.d("TAG", "nineOnclickListener: else")
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun eightOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        if (sum.isEmpty()) {
            binding.outputText.setTextColor(Color.GRAY)
            binding.outputText.textSize = 35F
            binding.editText.setTextColor(Color.BLACK)
            binding.editText.textSize = 50F
        }
        if (countNumber <= 18) {
            operation.limit_checker()
            inputText = binding.eight.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton) {
                "+" -> {
                    "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
                }
                "/" -> {
                    "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
                }
                else -> {
                    Log.d("TAG", "nineOnclickListener: else")
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun percentOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        val percent = (sumNumber.toFloat() / 100).toString()
        Log.d("TAG", "percentOnclickListener: $percent")
        sum = sum.replace(sumNumber, percent)
        sumNumber = percent
        binding.editText.setText(sum)
        when (pushButton) {
            "+" -> {
                "=${operation.addition(sumNumber)}".also { binding.outputText.text = it }
            }
            "-" -> {
                "=${operation.subtraction(sumNumber)}".also { binding.outputText.text = it }
            }
            "*" -> {
                "=${operation.multiplication(sumNumber)}".also { binding.outputText.text = it }
            }
            "/" -> {
                "=${operation.division(sumNumber)}".also { binding.outputText.text = it }
            }
            else -> {
                Log.d("TAG", "nineOnclickListener: else")
                "=$sum".also { binding.outputText.text = it }
            }
        }
    }

    fun equalOnclickListener(view: View) {
        vibrator.vibrate(30)
        binding.outputText.textSize = 50F
        binding.editText.textSize = 35F
        binding.outputText.setTextColor(Color.BLACK)
        binding.editText.setTextColor(Color.GRAY)
        sum = ""
        pushButton = ""
        inputText = ""
        pushButton = ""
        sumNumber = ""
        countNumber = 0
        flag = 1
        output_result = ""
    }


}