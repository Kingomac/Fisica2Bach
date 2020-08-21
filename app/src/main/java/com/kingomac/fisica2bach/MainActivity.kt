package com.kingomac.fisica2bach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "Física 2 Bach"
    }

    fun openView(view: View) {
        when(view.resources.getResourceEntryName(view.id)){
            "circularorbit"-> startActivity(Intent(this,CircularOrbit::class.java))
            "gravity"-> startActivity(Intent(this, Gravitation::class.java))
            "ellipticalorbit"-> startActivity(Intent(this, EllipticalOrbit::class.java))
            "relativity"-> startActivity(Intent(this, RelativityActivity::class.java))
            "quantum"-> startActivity(Intent(this, Quantum::class.java))
        }
    }
}