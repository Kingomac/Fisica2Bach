package com.example.testingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_elliptical_orbit.*
import kotlin.math.pow
import kotlin.math.sqrt

class EllipticalOrbit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elliptical_orbit)
    }

    fun calcular(view:View) {
        val gConst:Double = try {
            gConstText.text.toString().toDouble() * 10.0.pow(gConstEText.text.toString().toDouble())
        } catch (e:Exception) { 6.67 * 10.0.pow(-11) }
        val mass:Double = try {
            sunMassText.text.toString().toDouble() * 10.0.pow(sunMassEText.text.toString().toDouble())
        } catch (e:Exception) { 1.989 * 10.0.pow(30) }
        var velocity:Double = try {
            velocityText.text.toString().toDouble() * 10.0.pow(velocityEText.text.toString().toDouble())
        } catch (e:Exception) { -1.0 }
        var semiaxisA:Double = try {
            semiaxisAText.text.toString().toDouble() * 10.0.pow(semiaxisAText.text.toString().toDouble())
        } catch (e:Exception) { -1.0 }
        var afelio:Double = try {
            afelioText.text.toString().toDouble() * 10.0.pow(afelioEText.text.toString().toDouble())
        } catch (e:Exception) { -1.0 }
        var perihelio: Double = try {
            perihelioText.text.toString().toDouble() * 10.0.pow(perihelioEText.text.toString().toDouble())
        } catch (e:Exception) { -1.0 }
        var rdist:Double = try {
            rDistText.text.toString().toDouble() * 10.0.pow(rDistEText.text.toString().toDouble())
        } catch (e:Exception) { -1.0 }
        val planetMass: Double = try {
            planetMassText.text.toString().toDouble() * 10.0.pow(planetMassEText.text.toString().toDouble())
        } catch (e:Exception) { -1.0 }

        if(semiaxisA == -1.0 && afelio != -1.0 && perihelio != -1.0) semiaxisA = perihelio + afelio

        if(rdist != -1.0){
            if(velocity == -1.0) velocity = sqrt(2*gConst*mass*(1/rdist-1/(2*semiaxisA)))
        }

        angularMomentText.text = if(afelio != -1.0 && planetMass != -1.0) {
            (planetMass * afelio * sqrt(2*gConst*mass*(1/afelio-1/(2*semiaxisA)))).toString()
        } else { "No se pudo calcular el momento angular" }


        setTexts(velocityText, velocityEText, velocity.toString())
        setFields(semiaxisAText, semiaxisAEText, semiaxisA.toString())
    }
}