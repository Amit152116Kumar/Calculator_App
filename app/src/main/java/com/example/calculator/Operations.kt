package com.example.calculator

import android.util.Log
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.math.abs

class Operations(private val binding: ActivityMainBinding) {


    fun addition(number: String): String {

        var result: String
        var num1 = number
        var num2 = output_result

//       IF ONE OF THE NUMBERS ARE INT MAKING THEM FLOAT
        if (!num1.contains(".")) {
            num1 = num1.plus(".0")
        }
        if (!num2.contains(".")) {
            num2 = num2.plus(".0")
        }
        val list1 = num1.split(".").toMutableList()
        val list2 = num2.split(".").toMutableList()
//        ------------------------------------------------

//       MAKING DECIMAL PART EQUAL
        val n = list1[1].length
        val m = list2[1].length
        var diff = abs(n - m)
        if (n < m) {
            for (i in 0 until diff) {
                list1[1] = list1[1].plus("0")
            }
        } else {
            for (i in 0 until diff) {
                list2[1] = list2[1].plus("0")
            }
        }
//        ------------------------------------------------

//       MAKING INTEGRAL PART EQUAL
        val a = list1[0].length
        val b = list2[0].length
        list1[0] = list1[0].reversed()
        list2[0] = list2[0].reversed()
        diff = abs(a - b)
        if (a < b) {
            for (i in 0 until diff) {
                list1[0] = list1[0].plus("0")
            }
        } else {
            for (i in 0 until diff) {
                list2[0] = list2[0].plus("0")
            }
        }

//        ------------------------------------------------
        Log.d("TAG", "addition: $list1  \t $list2")

//        ADDING THE Decimal PART

        list1[1] = list1[1].reversed()
        list2[1] = list2[1].reversed()
        var carry = 0
        var resultFloat = ""
        for (i in list1[1].indices) {
            val sum = (list1[1][i].toInt() - 48) + (list2[1][i].toInt() - 48) + carry
            carry = sum / 10
            resultFloat += (sum % 10).toString()
        }
        resultFloat = resultFloat.reversed()

//        ADDING THE Integral PART

        var resultInt = ""
        for (i in list1[0].indices) {
            val sum = (list1[0][i].toInt() - 48) + (list2[0][i].toInt() - 48) + carry
            carry = sum / 10
            resultInt += (sum % 10).toString()
        }
        if (carry != 0) {
            resultInt += carry
        }
        resultInt = resultInt.reversed()


        result = resultInt.plus(".")
        result = result.plus(resultFloat)

        result = result.removeSuffix(".0")
        Log.d("TAG", "addition: $resultInt \t $resultFloat")
        return result
    }


    fun subtraction(number: String): String {
        var result: String
        var num1 = output_result
        var num2 = number

//       IF ONE OF THE NUMBERS ARE INT MAKING THEM FLOAT
        if (!num1.contains(".")) {
            num1 = num1.plus(".0")
        }
        if (!num2.contains(".")) {
            num2 = num2.plus(".0")
        }

//        TO DEFINE THE SIGN OF RESULT
        var sign = ""
        if (num1.toDouble() < num2.toDouble()) {
            sign = "-"
            val temp = num1
            num1 = num2
            num2 = temp
        }
        val list1 = num1.split(".").toMutableList()
        val list2 = num2.split(".").toMutableList()

//       MAKING DECIMAL PART EQUAL
        val n = list1[1].length
        val m = list2[1].length
        var diff = abs(n - m)
        if (n < m) {
            for (i in 0 until diff) {
                list1[1] = list1[1].plus("0")
            }
        } else {
            for (i in 0 until diff) {
                list2[1] = list2[1].plus("0")
            }
        }
//        ------------------------------------------------

//       MAKING INTEGRAL PART EQUAL
        val a = list1[0].length
        val b = list2[0].length
        list1[0] = list1[0].reversed()
        list2[0] = list2[0].reversed()
        diff = abs(a - b)
        if (a < b) {
            for (i in 0 until diff) {
                list1[0] = list1[0].plus("0")
            }
        } else {
            for (i in 0 until diff) {
                list2[0] = list2[0].plus("0")
            }
        }
        Log.d("TAG", "subtraciton: list1 = $list1  \t list2 = $list2")
//        SUBTRACTING THE DECIMAL PART
        list1[1] = list1[1].reversed()
        list2[1] = list2[1].reversed()
        var carry = 0
        var resultFloat = ""
        for (i in list1[1].indices) {
            var sub = (list1[1][i].toInt() - 48) - (list2[1][i].toInt() - 48) - carry
            if (sub < 0) {
                sub += 10
                carry = 1
            } else {
                carry = 0
            }

            resultFloat += sub.toString()
        }
        resultFloat = resultFloat.reversed()
        Log.d("TAG", "subtraction:  resultFloat = $resultFloat")

//        ADDING THE Integral PART

        var resultInt = ""
        for (i in list1[0].indices) {
            var sub = (list1[0][i].toInt() - 48) - (list2[0][i].toInt() - 48) - carry
            if (sub < 0) {
                sub += 10
                carry = 1
            } else {
                carry = 0
            }

            resultInt += sub.toString()
        }

        resultInt = resultInt.plus(sign)
        resultInt = resultInt.reversed()
        Log.d("TAG", "subtraction: resultInt = $resultInt  ")

        result = resultInt.plus(".")
        result = result.plus(resultFloat)
        Log.d("TAG", "subtraction: $result")
        result = result.removeSuffix(".0")
        return result
    }


    fun multiplication(number: String): String {

        var result = ""

        return result
    }

    fun limit_checker() {
        if (binding.editText.length() >= 12) {
            binding.editText.textSize = 35F
            binding.outputText.textSize = 35F
        }
    }


    fun division(number: String): String {
        var result = ""

        return result
    }
}


//  var num1=number
//           var num2= output_result
//           if (num1.length>num2.length)
//           {
//               val temp=num1
//               num1=num2
//               num2=temp
//           }
//           num1=num1.reversed()
//           num2=num2.reversed()
//           var carry = 0
//           var sum: Int
//
//           for (i in num1.indices) {
////             Converting the ASCII to number by -48
//               sum = (num1[i].toInt()-48) + (num2[i].toInt()-48)
//               carry = sum / 10
//               result += (sum % 10).toString()
//               Log.d("TAG", "sum: ${num1[i].toInt()} + ${num2[i].toInt()}")
//           }
//
//           for (i in num1.length until num2.length) {
//               sum =  num2[i].toInt()+ carry-48
//
//               carry = sum / 10
//               result += (sum % 10).toString()
//           }
//           if (carry != 0) {
//               result += carry
//           }
//           result=result.reversed()