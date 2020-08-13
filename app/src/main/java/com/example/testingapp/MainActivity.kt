package com.example.testingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.Exception
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "Física 2 Bach"
    }

    fun onCircularOrbitClick(view:View) {
        startActivity(Intent(this,CircularOrbit::class.java))
    }
    fun onRelativityClick(view: View) {
        startActivity(Intent(this, RelativityActivity::class.java))
    }
    fun onGravityClick(view: View) {
        startActivity(Intent(this, Gravitation::class.java))
    }
    fun onEllipticalOrbitClick(view: View) {
        startActivity(Intent(this, EllipticalOrbit::class.java))
    }
}