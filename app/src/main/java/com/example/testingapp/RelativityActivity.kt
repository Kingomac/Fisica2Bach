package com.example.testingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
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
    var ownedLength = 0.0
    var unownedLength = 0.0
    var mass = 0.0
    var energy = 0.0
    var ownedMass = 0.0
    var unownedMass = 0.0

    private fun setVars() {
        velocity = try {
            _velocity.text.toString().toDouble() *  10.0.pow(_velocityE.text.toString().toInt())
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
        } catch (e:Exception) {
            -1.0
        }
        xi = try {
            _xi.text.toString().toDouble()
        } catch (e:Exception) {
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
        ownedLength = try {
            _ownedLength.text.toString().toDouble() * 10.0.pow(_ownedLengthE.text.toString().toInt())
        } catch (e:Exception){
            -1.0
        }
        mass = try {
            _mass.text.toString().toDouble() * 10.0.pow(_massE.text.toString().toInt())
        } catch (e:Exception) {
            -1.0
        }
        energy = try {
            _energy.text.toString().toDouble() * 10.0.pow(_energyE.text.toString().toInt())
        } catch (e:Exception) {
            -1.0
        }
        ownedMass = try {
            _ownedMass.text.toString().toDouble() * 10.0.pow(_unownedMassE.text.toString().toInt())
        } catch (e:Exception) {
            -1.0
        }
        unownedMass = try {
            _unownedMass.text.toString().toDouble() * 10.0.pow(_unownedMassE.text.toString().toInt())
        } catch (e:Exception) {
            -1.0
        }
    }
    private fun setTexts() {
        _xi.setText(xi.toString())
        _beta.setText(beta.toString())
        setText(_ownedTime, _ownedTimeE, ownedTime.toString())
        setText(_unownedTime, _unownedTimeE, unownedTime.toString())
        setText(_ownedLength, _ownedLengthE, ownedLength.toString())
        setText(_unownedLength, _unownedLengthE, unownedLength.toString())
        setText(_mass, _massE, mass.toString())
        setText(_energy, _energyE, energy.toString())
    }
    private fun magic() {
        if(beta !== -1.0 && lightVel !== -1.0) {
            velocity = lightVel * beta
        }
        if(velocity !== -1.0 && lightVel !== -1.0) {
            beta = velocity / lightVel
            xi = 1/sqrt(1-beta.pow(2))
        }
        if(beta !== -1.0 && xi !== -1.0) {
            if (ownedTime !== -1.0) unownedTime = xi * ownedTime
            else ownedTime = unownedTime / xi

            if(ownedLength !== -1.0) unownedLength = ownedLength / xi
            else ownedLength = xi * unownedLength
        }
        if(lightVel !== -1.0 && mass !== -1.0) {
            energy = if(velocity == -1.0) mass * lightVel.pow(2)
            else sqrt((mass * lightVel.pow(2)).pow(2) + (mass * velocity * lightVel).pow(2))
        }
        if(xi !== -1.0) {
            if(ownedMass !== -1.0) unownedMass = xi * ownedMass
            else ownedMass = unownedMass / xi
        }
    }
    fun onClick(view: View) {
        setVars()
        magic()
        setTexts()
    }
    fun onReturnClick(view: View){
        startActivity(Intent(this, MainActivity::class.java))
    }
}
