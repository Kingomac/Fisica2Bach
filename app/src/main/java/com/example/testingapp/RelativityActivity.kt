package com.example.testingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_relativity.*
import kotlin.math.pow
import kotlin.math.sqrt

class RelativityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relativity)
    }

    var velocity:Double = 0.0
    var lightVel:Double = 0.0
    var beta:Double = 0.0
    var xi:Double = 0.0
    var ownedTime = 0.0
    var unownedTime = 0.0

    private fun setVars() {
        velocity = try {
            _velocity.text.toString().toDouble() *  10.0.pow(findViewById<EditText>(R.id._velocityE).text.toString().toInt())
        } catch (e:Exception) {
            -1.0
        }
        lightVel = try {
            _lightVel.text.toString().toDouble() * 10.0.pow(_lightVelE.text.toString().toInt())
        } catch (e:Exception) {
            -1.0
        }
        beta = try {
            _beta.text.toString().toDouble()
        } catch (e: Exception) {
            -1.0
        }
        xi = try {
            _xi.text.toString().toDouble()
        } catch (e: Exception) {
            -1.0
        }
        ownedTime = try {
            _ownedTime.text.toString().toDouble() * 10.0.pow(_ownedTimeE.text.toString().toInt())
        } catch (e:Exception) {
            -1.0
        }
        unownedTime = try {
            _unownedTime.text.toString().toDouble() * 10.0.pow(_unownedTimeE.text.toString().toInt())
        } catch (e:Exception) {
            -1.0
        }
        Log.d("UNOWNEDTIME", unownedTime.toString())
    }
    private fun setTexts() {
        _xi.setText(xi.toString())
        _beta.setText(beta.toString())
        setText(_ownedTime, _ownedTimeE, ownedTime.toString())
        setText(_unownedTime, _unownedTimeE, unownedTime.toString())
        Log.d("VARS INFO","unowned:$unownedTime || owned: ${ownedTime.toString()} || xi:$xi")
    }
    private fun magic() {
        if(beta !== -1.0 && lightVel !== -1.0) {
            velocity = lightVel * beta
        }
        if(velocity !== -1.0 && lightVel !== -1.0) {
            beta = velocity / lightVel
            xi = 1/sqrt(1-beta.pow(2))
        }
        Log.d("VARS INFO","unowned:$unownedTime || owned: $ownedTime || xi:$xi")
        if(beta !== -1.0 && xi !== -1.0) {
            if(ownedTime !== -1.0) unownedTime = xi * ownedTime
            if(unownedTime !== -1.0) ownedTime = unownedTime / xi
        }
        Log.d("VARS INFO","unowned:$unownedTime || owned: $ownedTime || xi:$xi")
    }
    public fun onClick(view: View) {
        setVars()
        magic()
        setTexts()
    }
}
