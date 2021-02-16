package com.example.asignacion4_calculadoraimc_garcia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    //Variables
    val etWeight: EditText = (findViewById(R.id.weight)) as EditText
    val etHeight: EditText = (findViewById(R.id.height)) as EditText
    val tvIMC: TextView = (findViewById(R.id.imc)) as TextView
    val tvRange: TextView = (findViewById(R.id.range)) as TextView
    val button: Button = (findViewById(R.id.buttonCalcularIMC)) as Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //evento button click o clicklistener
        button.setOnClickListener {
            var values = calcularIMC(etWeight.text.toString(), etHeight.text.toString())

            tvIMC.text = values[0]
            tvRange.text = values[1]
        }
    }

    //función
    fun calcularIMC(weightString: String, heightString: String): List<String>  {
        val imc: Double
        var range: String = ""

        try {
            val weight: Double = weightString.toDouble()
            val height: Double = heightString.toDouble()

            imc = (weight / Math.pow(height, 2.0))

            when (imc) {
                in 0.0 .. 18.5 -> range = "Bajo peso"
                in 18.5 .. 24.9 -> range = "Peso normal"
                in 25.0 .. 29.9 -> range = "Sobrepeso"
                in 30.0 .. 34.9 -> range = "Obesidad grado 1"
                in 35.0 .. 39.9 -> range = "Obesidad grado 2"
                else -> range = "Obesidad grado 3"
            }
        } catch (e: Exception) {
            return listOf("", "")
        }

        return listOf(imc.toString(), range)
    }
}