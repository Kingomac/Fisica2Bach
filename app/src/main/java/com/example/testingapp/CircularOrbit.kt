package com.example.testingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_circular_orbit.*
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class CircularOrbit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_orbit)

        ids = arrayOf(findViewById(R.id.gConst), findViewById(R.id.planetMass), findViewById(R.id.vel), findViewById(R.id.satelMass), findViewById(R.id.centerDist),
            findViewById(R.id.angularVel), findViewById(R.id.period), findViewById(R.id.linearMoment), findViewById(R.id.forceMoment), findViewById(R.id.cineticEnergy),
            findViewById(R.id.potentialEnergy), findViewById(R.id.mechanicalEnergy), findViewById(R.id.g0), findViewById(R.id.planetRadio))
        idsExp = arrayOf(findViewById(R.id.gConstExp), findViewById(R.id.planetMassExp), findViewById(R.id.vel), findViewById(R.id.satelMassExp),
            findViewById(R.id.centerDistExp), findViewById(R.id.angularVelExp), findViewById(R.id.periodExp), findViewById(R.id.linearMomentExp), findViewById(R.id.forceMomentExp), findViewById(R.id.cineticEnergyExp),
            findViewById(R.id.potentialEnergyExp), findViewById(R.id.mechanicalEnergyExp), findViewById(R.id.g0Exp), findViewById(R.id.planetRadioExp))
        val button = findViewById<Button>(R.id.button);
        val button2 = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            setVariables()
            magic()
            magic()
            setTexts()
        }
        button2.setOnClickListener{
            for(i in ids){
                i.setText("")
            }
            for(i in idsExp){
                i.setText("")
            }
        }
    }

    private var gConst:Double = 0.0
    private var planetMass:Double = 0.0
    private var satelVel:Double = 0.0
    private var satelMass:Double = 0.0
    private var centerDist:Double = 0.0
    private var angularVel:Double = 0.0
    private var period:Double = 0.0
    private var freq:Double = 0.0
    private var angularMoment:Double = 0.0
    private var linearMoment:Double = 0.0
    private var forceMoment:Double = 0.0
    private var cineticEnergy:Double = 0.0
    private var potentialEnergy:Double = 0.0
    private var mechanicEnergy:Double = 0.0
    private var g0:Double = 0.0
    private var planetRadio:Double = 0.0

    private var ids:Array<EditText> = emptyArray()
    private var idsExp:Array<EditText> = emptyArray()

    private fun setTexts() {
        setText(findViewById<EditText>(R.id.gConst), findViewById<EditText>(R.id.gConstExp), gConst.toString())
        setText(findViewById<EditText>(R.id.vel), findViewById<EditText>(R.id.velExp), satelVel.toString())
        setText(findViewById<EditText>(R.id.planetMass), findViewById<EditText>(R.id.planetMassExp), planetMass.toString())
        setText(findViewById<EditText>(R.id.satelMass), findViewById<EditText>(R.id.satelMassExp), satelMass.toString())
        setText(findViewById<EditText>(R.id.centerDist), findViewById<EditText>(R.id.centerDistExp), centerDist.toString())
        setText(findViewById<EditText>(R.id.angularVel), findViewById<EditText>(R.id.angularVelExp), angularVel.toString())
        setText(findViewById<EditText>(R.id.linearMoment),findViewById<EditText>(R.id.linearMomentExp), linearMoment.toString())
        setText(findViewById<EditText>(R.id.freq), findViewById<EditText>(R.id.freqExp), freq.toString())
        setText(findViewById<EditText>(R.id.period), findViewById<EditText>(R.id.periodExp), period.toString())
        setText(findViewById<EditText>(R.id.forceMoment), findViewById<EditText>(R.id.forceMomentExp), forceMoment.toString())
        setText(findViewById<EditText>(R.id.cineticEnergy), findViewById<EditText>(R.id.cineticEnergyExp), cineticEnergy.toString())
        setText(findViewById<EditText>(R.id.potentialEnergy), findViewById<EditText>(R.id.potentialEnergyExp), potentialEnergy.toString())
        setText(findViewById<EditText>(R.id.mechanicalEnergy), findViewById<EditText>(R.id.mechanicalEnergyExp), mechanicEnergy.toString())
        setText(findViewById<EditText>(R.id.g0), findViewById<EditText>(R.id.g0Exp), g0.toString())
        setText(findViewById<EditText>(R.id.planetRadio), findViewById<EditText>(R.id.planetRadioExp), planetRadio.toString())
        setText(findViewById<EditText>(R.id.angularMoment), findViewById<EditText>(R.id.angularMomentExp), angularMoment.toString())
    }



    private fun setVariables() {
        gConst = try {
            getNumExponential(findViewById<EditText>(R.id.gConst).text.toString().toDouble(), findViewById<EditText>(R.id.gConstExp).text.toString().toInt())
        } catch (e: Exception){
            -1.0
        }
        planetMass = try {
            getNumExponential(findViewById<EditText>(R.id.planetMass).text.toString().toDouble(), findViewById<EditText>(R.id.planetMassExp).text.toString().toInt())
        } catch (e: Exception){
            -1.0
        }
        satelVel = try {
            getNumExponential(
                findViewById<EditText>(R.id.vel).text.toString().toDouble(),
                findViewById<EditText>(R.id.velExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        satelMass = try {
            getNumExponential(
                findViewById<EditText>(R.id.satelMass).text.toString().toDouble(),
                findViewById<EditText>(R.id.satelMassExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        centerDist =  try {
            getNumExponential(
                findViewById<EditText>(R.id.centerDist).text.toString().toDouble(),
                findViewById<EditText>(R.id.centerDistExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        angularVel = try {
            getNumExponential(
                findViewById<EditText>(R.id.angularVel).text.toString().toDouble(),
                findViewById<EditText>(R.id.angularVelExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        period = try {
            getNumExponential(
                findViewById<EditText>(R.id.period).text.toString().toDouble(),
                findViewById<EditText>(R.id.periodExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        freq = try {
            getNumExponential(
                findViewById<EditText>(R.id.freq).text.toString().toDouble(),
                findViewById<EditText>(R.id.freqExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        angularMoment = try {
            getNumExponential(
                findViewById<EditText>(R.id.angularMoment).text.toString().toDouble(),
                findViewById<EditText>(R.id.angularMomentExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        linearMoment = try {
            getNumExponential(
                findViewById<EditText>(R.id.linearMoment).text.toString().toDouble(),
                findViewById<EditText>(R.id.linearMomentExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        forceMoment = try {
            getNumExponential(
                findViewById<EditText>(R.id.forceMoment).text.toString().toDouble(),
                findViewById<EditText>(R.id.forceMomentExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        cineticEnergy = try {
            getNumExponential(
                findViewById<EditText>(R.id.cineticEnergy).text.toString().toDouble(),
                findViewById<EditText>(R.id.cineticEnergyExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        potentialEnergy = try {
            getNumExponential(
                findViewById<EditText>(R.id.potentialEnergy).text.toString().toDouble(),
                findViewById<EditText>(R.id.potentialEnergyExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        mechanicEnergy = try {
            getNumExponential(
                findViewById<EditText>(R.id.mechanicalEnergy).text.toString().toDouble(),
                findViewById<EditText>(R.id.mechanicalEnergyExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        g0 = try {
            getNumExponential(
                findViewById<EditText>(R.id.g0).text.toString().toDouble(),
                findViewById<EditText>(R.id.g0Exp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
        planetRadio = try {
            getNumExponential(
                findViewById<EditText>(R.id.planetRadio).text.toString().toDouble(),
                findViewById<EditText>(R.id.planetRadioExp).text.toString().toInt()
            )
        } catch (e: Exception){
            -1.0
        }
    }
    private fun magic() {
        var GM:Double = -1.0
        if(gConst != -1.0 && planetMass != -1.0){
            GM = gConst * planetMass
        }
        else if (g0 !== -1.0 && planetRadio !== -1.0){
            GM = g0 * planetRadio.pow(2)
        }
        if(GM != -1.0 && satelVel != -1.0 && centerDist == -1.0){
            centerDist = GM / satelVel.pow(2)
        }
        if(GM != -1.0 && centerDist != -1.0){
            satelVel = sqrt(GM / centerDist)
            angularVel = satelVel / centerDist
            if(planetMass != -1.0){
                potentialEnergy = - GM * planetMass / centerDist
            }
            if(satelMass != -1.0){
                cineticEnergy = 0.5 * satelMass * satelVel.pow(2)
            }
            if(cineticEnergy != -1.0 && potentialEnergy != -1.0){
                mechanicEnergy = cineticEnergy + potentialEnergy
            }
        }
        if(satelVel != -1.0 && centerDist != -1.0){
            period = 2 * PI * centerDist / satelVel
            freq = 1 / period
        }
        if(satelMass != -1.0 && centerDist != -1.0 && satelVel != -1.0){
            angularMoment = satelMass * centerDist * satelVel
        }
        if(angularVel != -1.0 && satelVel != -1.0){
            centerDist = satelVel / angularVel
        }
        if(period != -1.0){
            angularVel = 2 * PI / period
        }
        if(satelMass != -1.0 && satelVel != -1.0){
            linearMoment = satelMass * satelVel
        }
        if(planetRadio != -1.0){
            g0 = GM / planetRadio.pow(2)
        }
        if(g0 != -1.0 && planetRadio == -1.0){
            planetRadio = sqrt(GM/g0)
        }
        forceMoment = 0.0
    }

    private fun getNumExponential(num:Double, exp:Int): Double {
        return num * 10.0.pow(exp)
    }
    public fun onReturnClick(view:View){
        startActivity(Intent("MainActivity"))
    }

}
