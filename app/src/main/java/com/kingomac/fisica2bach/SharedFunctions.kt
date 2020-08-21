package com.kingomac.fisica2bach

import android.widget.EditText
import android.widget.TextView
import kotlin.math.abs
import kotlin.math.sqrt

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
fun setFields(numET:EditText, numExET:EditText, value:String){
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

fun setTexts(numET:TextView, numExET:TextView, value:String){
    var num = scienceNotationNumber(number = 0.0, exponential = 0)
    if(value.contains('E')) {
        num.number = value.substring(0, value.indexOf("E")).toDouble()
        num.exponential = value.substring(value.indexOf("E") + 1, value.length).toInt()
    } else {
        num = getScienceNotation(value.toDouble())
    }
    numET.text = num.number.toString()
    numExET.text = num.exponential.toString()
}


abstract class Body (open val id: Int, open val position: Vector) {
}

data class GravityBody(override val id: Int, val mass:Double, override var position: Vector) : Body(id, position)

class Vector {
    var x: Double
    var y: Double
    var z: Double
    constructor(x:Double, y:Double, z:Double) {
        this.x = x
        this.y = y
        this.z = z
    }
    companion object {
        fun getModule(vector:Vector): Double {
            return sqrt(vector.x * vector.x + vector.y * vector.y + vector.z * vector.z)
        }
        fun getUnit(vector:Vector): Vector {
            val module = this.getModule(vector)
            return Vector(vector.x/module, vector.y/module, vector.z/module)
        }
        fun getVector(posA:Vector, posB:Vector): Vector {
            return Vector(posB.x-posA.x, posB.y-posA.y, posB.z-posA.z)
        }
        fun sum(a:Vector, b:Vector):Vector {
            return Vector(a.x+b.x,a.y+b.y,a.z+b.z)
        }
        fun subtraction(a:Vector, b:Vector):Vector {
            return Vector(a.x-b.x,a.y-b.y,a.z-b.z)
        }
        fun crossProduct(a:Vector, b:Vector):Vector { //Producto vectorial
            return Vector(a.y*b.z-b.y*a.z,-a.x*b.z+b.x*a.z,a.x*b.y-b.x*a.y)
        }
        fun scalarProduct(a:Vector, b:Vector):Double {
            return a.x*b.x+a.y*b.y+a.z*b.z
        }
        fun vectorMultipliedByDouble(n:Double, v:Vector):Vector {
            return Vector(n*v.x, n*v.y, n*v.z)
        }
    }
}