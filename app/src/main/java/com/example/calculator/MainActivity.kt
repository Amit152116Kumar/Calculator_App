package com.example.calculator


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.math.PI


var output_result: String = ""

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var vibrator: Vibrator

    //    TO STORE ALL OPERATIONS AND SHOW ON EDIT TEXT
    private var sum = ""

    //    TO STORE CLICKED BUTTON INPUT
    private var inputText = ""

    //    FOR THE OPERATION BUTTON
    private var pushButton = arrayListOf("")

    //    TO STORE ONE DIGIT
    private var sumNumber = ""

    private var listOutput = arrayListOf("")
    private var listNumber = arrayListOf<String>()

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
        binding.inverseTrigno.setOnClickListener {
            vibrator.vibrate(30)
            if (binding.sin.text.toString() == getString(R.string.sin)) {
                binding.sin.setText(R.string.sin_inverse)
                binding.cos.setText(R.string.cos_inverse)
                binding.tan.setText(R.string.tan_inverse)
            } else if (binding.sin.text.toString() == getString(R.string.sin_inverse)) {
                binding.sin.setText(R.string.sin)
                binding.cos.setText(R.string.cos)
                binding.tan.setText(R.string.tan)
            }
        }
    }

    fun plusOnclickListener(view: View) {
        changeStyle()
        vibrator.vibrate(30)
        countNumber = 0
        listNumber.add(sumNumber)
        sumNumber = ""
        if (flag == 0) {
            flag = 1
            pushButton.add("+")
            if (sum != "") {
                output_result = binding.outputText.text.toString().removePrefix("=")
                listOutput.add(output_result)
                inputText = binding.plus.text as String
                sum += inputText
                binding.editText.setText(sum)
            }
        }
    }

    fun minusOnclickListener(view: View) {
        changeStyle()
        vibrator.vibrate(30)
        countNumber = 0
        listNumber.add(sumNumber)
        sumNumber = ""
        if (flag == 0) {
            flag = 1
            pushButton.add("-")
            if (sum != "") {
                output_result = binding.outputText.text.toString().removePrefix("=")
                listOutput.add(output_result)
                inputText = binding.minus.text as String
                sum += inputText
                binding.editText.setText(sum)
            }
        }
    }

    fun multiplyOnclickListener(view: View) {
        changeStyle()
        vibrator.vibrate(30)
        countNumber = 0
        listNumber.add(sumNumber)
        sumNumber = ""
        if (flag == 0) {
            flag = 1
            pushButton.add("*")
            if (sum != "") {
                output_result = binding.outputText.text.toString().removePrefix("=")
                listOutput.add(output_result)
                inputText = binding.multiply.text as String
                sum += inputText
                binding.editText.setText(sum)
            }
        }
    }

    fun divideOnclickListener(view: View) {
        changeStyle()
        vibrator.vibrate(30)
        countNumber = 0
        listNumber.add(sumNumber)
        sumNumber = ""
        if (flag == 0) {
            flag = 1
            pushButton.add("/")
            if (sum != "") {
                output_result = binding.outputText.text.toString().removePrefix("=")
                listOutput.add(output_result)
                inputText = binding.divide.text as String
                sum += inputText
                binding.editText.setText(sum)
            }
        }

    }


    fun nineOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (countNumber <= 12) {
            limitChecker()
            inputText = binding.nine.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton[pushButton.lastIndex]) {
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
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }


    fun dotOnclickListener(view: View) {
        vibrator.vibrate(30)
        changeStyle()
        flag = 0
        if (sumNumber.isNotEmpty()) {
            if (!sumNumber.contains(".")) {
                limitChecker()
                if (countNumber <= 12) {
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
        pushButton = arrayListOf("")
        sumNumber = ""
        listOutput = arrayListOf("")
        countNumber = 0
        flag = 1
        output_result = ""
        binding.editText.setText("")
        binding.outputText.text = "0"
        listNumber.clear()
        binding.outputText.setTextColor(Color.BLACK)
        binding.editText.setTextColor(Color.BLACK)
    }


    fun zeroOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (sum.isNotEmpty()) {
            limitChecker()
            if (countNumber <= 12) {
                inputText = binding.zero.text as String
                sumNumber += inputText
                sum += inputText
                binding.editText.setText(sum)
                when (pushButton[pushButton.lastIndex]) {
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
                        "=$sum".also { binding.outputText.text = it }
                    }
                }
                countNumber++
            }

        }
    }

    fun deleteOnclickListener(view: View) {
        vibrator.vibrate(30)
        if (sum.isEmpty()) {
            return
        }
        val number = "[0-9.]".toRegex()
        val operator = "[+*/-]".toRegex()
        val lastChar: Char = sum[sum.length - 1]
        if (lastChar.toString().matches(number)) {
            sum = sum.removeSuffix(lastChar.toString())
            if (sumNumber.isEmpty()) {

                sumNumber = listNumber[listNumber.lastIndex]
                output_result = listOutput[listOutput.lastIndex]
                listNumber.removeAt(listNumber.lastIndex)
                Log.d("TAG", "deleteOnclickListener: $sumNumber\n $output_result")
            }
            sumNumber = sumNumber.removeSuffix(lastChar.toString())
            binding.editText.setText(sum)
            countNumber--
        } else if (lastChar.toString().matches(operator)) {
            pushButton.removeAt(pushButton.lastIndex)
            sum = sum.removeSuffix(lastChar.toString())
            listOutput.removeAt(listOutput.lastIndex)
            sumNumber = listNumber[listNumber.lastIndex]
            output_result = listOutput[listOutput.lastIndex]
            Log.d("TAG", "delete: $sumNumber $output_result")
            binding.editText.setText(sum)
        }
        if (sumNumber.isNotEmpty()) {
            when (pushButton[pushButton.lastIndex]) {
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
                    "=$sum".also { binding.outputText.text = it }
                }
            }
        } else {
            when (pushButton[pushButton.lastIndex]) {
                "+" -> {
                    "=${operation.addition(sumNumber + "0")}".also { binding.outputText.text = it }
                }
                "-" -> {
                    "=${operation.subtraction(sumNumber + "0")}".also {
                        binding.outputText.text = it
                    }
                }
                "*" -> {
                    "=${operation.multiplication(sumNumber + "1")}".also {
                        binding.outputText.text = it
                    }
                }
                "/" -> {
                    "=${operation.division(sumNumber + "1")}".also { binding.outputText.text = it }
                }
                else -> {
                    "=$sum".also { binding.outputText.text = it }
                }
            }
        }


    }

    fun oneOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (countNumber <= 12) {
            limitChecker()
            inputText = binding.one.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton[pushButton.lastIndex]) {
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
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun twoOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (countNumber <= 12) {
            limitChecker()
            inputText = binding.two.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton[pushButton.lastIndex]) {
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
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun threeOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (countNumber <= 12) {
            limitChecker()
            inputText = binding.three.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton[pushButton.lastIndex]) {
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
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun fourOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (countNumber <= 12) {
            limitChecker()
            inputText = binding.four.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton[pushButton.lastIndex]) {
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

                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun fiveOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (countNumber <= 12) {
            limitChecker()
            inputText = binding.five.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton[pushButton.lastIndex]) {
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
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun sixOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (countNumber <= 12) {
            limitChecker()
            inputText = binding.six.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton[pushButton.lastIndex]) {
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
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun sevenOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (countNumber <= 12) {
            limitChecker()
            inputText = binding.seven.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton[pushButton.lastIndex]) {
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

                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun eightOnclickListener(view: View) {
        vibrator.vibrate(30)
        flag = 0
        changeStyle()
        if (countNumber <= 12) {
            limitChecker()
            inputText = binding.eight.text as String
            sumNumber += inputText
            sum += inputText
            binding.editText.setText(sum)
            when (pushButton[pushButton.lastIndex]) {
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
                    "=$sum".also { binding.outputText.text = it }
                }
            }
            countNumber++
        }
    }

    fun percentOnclickListener(view: View) {
        vibrator.vibrate(30)
        changeStyle()
        flag = 0
        val percent = (sumNumber.toFloat() / 100).toString()
        sum = sum.replace(sumNumber, percent)
        sumNumber = percent
        binding.editText.setText(sum)
        when (pushButton[pushButton.lastIndex]) {
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
        sumNumber = binding.outputText.text.toString().removePrefix("=")
        sum = sumNumber
        pushButton = arrayListOf("")
        listOutput = arrayListOf("")
        listNumber.clear()
        inputText = ""
        countNumber = 0
        flag = 0
        output_result = ""
    }

    private fun changeStyle() {
        binding.outputText.setTextColor(Color.GRAY)
        binding.outputText.textSize = 35F
        binding.editText.setTextColor(Color.BLACK)
        binding.editText.textSize = 50F
    }

    private fun limitChecker() {
        if (binding.editText.length() >= 12) {
            binding.editText.textSize = 35F
            binding.outputText.textSize = 35F
        }
        if (binding.editText.length() >= 18) {
            binding.editText.textSize = 20F
            binding.outputText.textSize = 20F
        }

    }

}