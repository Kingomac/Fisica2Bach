package com.example.testingapp

import android.widget.EditText
import kotlin.math.abs

public data class scienceNotationNumber (
    var number: Double,
    var exponential: Int
)
public fun getScienceNotation(num:Double): scienceNotationNumber {
    var n = num
    var e = 0
    if(abs(num) < 10 && abs(num) >= 0){
        n = num
        e = 0
    }
    else {
        if (num > 1) {
            while (abs(n) >= 10) {
                n /= 10
                e++
            }
        } else {
            while (abs(n) <= 0.9) {
                n *= 10
                e--
            }
        }
    }
    return scienceNotationNumber(n, e)
}
public fun setText(numET:EditText, numExET:EditText, value:String){
    var num = scienceNotationNumber(number = 0.0, exponential = 0)
    if(value.contains('E')) {
        num.number = value.substring(0, value.indexOf("E")).toDouble()
        num.exponential = value.substring(value.indexOf("E") + 1, value.length).toInt()
    } else {
        num = getScienceNotation(value.toDouble())
    }
    numET.setText(num.number.toString())
    numExET.setText(num.exponential.toString())
}