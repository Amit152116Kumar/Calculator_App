package com.example.calculator

import com.example.calculator.databinding.ActivityMainBinding

class Operations(private val binding: ActivityMainBinding) {


    fun addition(number: String): String {
        var result: String
        val num1 = number.toDouble()
        val num2 = output_result.toDouble()
        result = (num2 + num1).toString()
        result = result.removeSuffix(".0")
        return result
    }


    fun subtraction(number: String): String {
        var result: String
        val num2 = output_result.toDouble()
        val num1 = number.toDouble()
        result = (num2 - num1).toString()
        result = result.removeSuffix(".0")
        return result
    }


    fun multiplication(number: String): String {

        var result: String
        val num1 = number.toDouble()
        val num2 = output_result.toDouble()

        result = (num2 * num1).toString()
        result = result.removeSuffix(".0")
        return result
    }

    fun division(number: String): String {
        var result: String
        val num1 = number.toDouble()
        val num2 = output_result.toDouble()

        result = (num2 / num1).toString()
        result = result.removeSuffix(".0")
        return result
    }
}

