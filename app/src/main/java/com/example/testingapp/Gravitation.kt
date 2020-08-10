package com.example.testingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_gravitation.*
import kotlin.math.pow

class Gravitation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gravitation)

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerAdapter(arrayListOf())
        bodyList.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
    private lateinit var  viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var bodies: ArrayList<GravityBody> = arrayListOf()
    fun addBody(view:View) {
        try {
            bodies.add(
                GravityBody(
                    bodies.size + 1,
                    mass.text.toString().toDouble(),
                    Vector(
                        pos_x.text.toString().toDouble(),
                        pos_y.text.toString().toDouble(),
                        pos_z.text.toString().toDouble()
                    )
                )
            )
            mass.setText("")
            pos_x.setText("")
            pos_y.setText("")
            pos_z.setText("")
            bodyList.adapter = RecyclerAdapter(bodies)
        } catch (e:Exception) {
            Toast.makeText(this.applicationContext,"Escribe los ceros vago", Toast.LENGTH_SHORT).show()
        }
    }
    fun gravityFieldInPoint(view:View) {
        val point = try {
            Vector(point_x.text.toString().toDouble(), point_y.text.toString().toDouble(), point_z.text.toString().toDouble())
        } catch (e:Exception) {
            Toast.makeText(this.applicationContext, "Punto no encontrado. Escribe los ceros", Toast.LENGTH_SHORT).show()
            return
        }
        val gConst = try {
            inputGConst.text.toString().toDouble() * inputGConstE.text.toString().toDouble()
        } catch (e:Exception) {
            6.67 * 10.0.pow(-11)
        }
        var result = Vector(0.0,0.0,0.0)
        for(b in bodies) {
            val distVector = Vector.subtraction(b.position,point)
            val field = gConst * b.mass / Vector.getModule(distVector)
            val finalVector = Vector.vectorMultipliedByDouble(field,Vector.getUnit(distVector))
            result = Vector.sum(result, finalVector)
        }
        pointResult.text = "(${result.x}, ${result.y}, ${result.z})"
    }
}