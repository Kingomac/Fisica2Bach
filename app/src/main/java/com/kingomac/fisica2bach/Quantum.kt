package com.kingomac.fisica2bach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_quantum.*
import kotlin.math.pow

class Quantum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quantum)
    }

    private var plankConst:Double = 0.0
    private var electronCharge: Double = 0.0
    private var lightVel: Double = 0.0


    fun calcular(view: View) {
        calculatePhotoelectricEffect()
        calcularRadiacion()
    }

    private var radEnergia: Double? = null
    private var radFrec: Double? = null

    private fun calcularRadiacion() {
        radEnergia = try {
            radEnergiaText.text.toString().toDouble() * 10.0.pow(radEnergiaEText.text.toString().toDouble())
        } catch (e:Exception) { null }
        radFrec = try {
            radFrecText.text.toString().toDouble() * 10.0.pow(radFrecEText.text.toString().toDouble())
        } catch (e:Exception) { null }

        for (i in 1 until 2) {
            if(radFrec != null) radEnergia = radFrec!! * plankConst
            if(radEnergia != null) radFrec = radEnergia!! / plankConst
        }
        setTexts(radEnergiaText, radEnergiaEText, radEnergia.toString())
        setTexts(radFrecText, radFrecEText, radFrec.toString())
    }

    private var waveLength: Double? = null
    private var photonFreq: Double? = null
    private var photonEnergy: Double? = null
    private var extractionWork: Double? = null
    private var kineticEnergy: Double? = null
    private var brakingPotential: Double? = null
    private var umbralFreq: Double? = null

    private fun calculatePhotoelectricEffect() {
        plankConst = try {
            plankConstText.text.toString().toDouble() * 10.0.pow(plankConstEText.text.toString().toDouble())
        } catch (e:Exception) { 6.626 * 10.0.pow(-34) }
        electronCharge = try {
            electronChargeText.text.toString().toDouble() * 10.0.pow(electronChargeEText.text.toString().toDouble())
        } catch (e:Exception) { 1.602 * 10.0.pow(-19) }
        lightVel = try {
            velLuzText.text.toString().toDouble() * 10.0.pow(velLuzEText.text.toString().toDouble())
        } catch (e:Exception) { 3.0 * 10.0.pow(8) }
        waveLength = try {
            longitudOndaText.text.toString().toDouble() * 10.0.pow(longitudOndaEText.text.toString().toDouble())
        } catch (e:Exception) { null }
        photonFreq = try {
            frecText.text.toString().toDouble() * 10.0.pow(frecEText.text.toString().toDouble())
        } catch (e:Exception) { null }
        photonEnergy = try {
            photonEnergyText.text.toString().toDouble() * 10.0.pow(photonEnergyEText.text.toString().toDouble())
        } catch (e:Exception) { null }
        extractionWork= try {
            extractionWorkText.text.toString().toDouble() * 10.0.pow(extractionWorkEText.text.toString().toDouble())
        } catch (e:Exception) { null }
        kineticEnergy = try {
            kineticEnergyText.text.toString().toDouble() * 10.0.pow(kineticEnergyEText.toString().toDouble())
        } catch (e:Exception) { null }
        brakingPotential = try {
            brakingPotentialText.text.toString().toDouble() * 10.0.pow(brakingPotentialEText.text.toString().toDouble())
        } catch (e:Exception) { null }
        umbralFreq = try {
            umbralFreqText.text.toString().toDouble() * 10.0.pow(umbralFreqEText.text.toString().toDouble())
        } catch (e:Exception) { null }

        for(i in 1 until 2) {
            if(photonFreq != null) photonEnergy = plankConst * photonFreq!!
            if(photonFreq == null && photonEnergy != null) photonFreq = photonEnergy!!/plankConst

            if(waveLength != null) photonFreq = lightVel/waveLength!!
            if(photonFreq != null) waveLength = lightVel/photonFreq!!

            if(extractionWork != null) umbralFreq = extractionWork!!/plankConst
            if(umbralFreq != null) extractionWork = plankConst * umbralFreq!!
            if(brakingPotential != null) kineticEnergy = electronCharge * brakingPotential!!
            if(kineticEnergy != null) brakingPotential = kineticEnergy!! / electronCharge

            if(kineticEnergy != null && extractionWork != null) photonEnergy = kineticEnergy!! + extractionWork!!
            if(photonEnergy != null && kineticEnergy != null) extractionWork = photonEnergy!! - kineticEnergy!!
            if(photonEnergy!= null && extractionWork != null) kineticEnergy = photonEnergy!! - extractionWork!!
        }

        setTexts(longitudOndaText, longitudOndaEText, waveLength.toString())
        setTexts(frecText, frecEText, photonFreq.toString())
        setTexts(photonEnergyText,photonEnergyEText,photonEnergy.toString())
        setTexts(umbralFreqText,umbralFreqEText,umbralFreq.toString())
        setTexts(extractionWorkText,extractionWorkEText,extractionWork.toString())
        setTexts(kineticEnergyText,kineticEnergyEText,kineticEnergy.toString())
        setTexts(brakingPotentialText, brakingPotentialEText, brakingPotential.toString())
    }
}