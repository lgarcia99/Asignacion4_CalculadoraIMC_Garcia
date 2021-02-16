package com.example.asignacion4_calculadoraimc_garcia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    //Variables

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etWeight: EditText = (findViewById(R.id.weight)) as EditText
        val etHeight: EditText = (findViewById(R.id.height)) as EditText
        val tvIMC: TextView = (findViewById(R.id.imc)) as TextView
        val tvRange: TextView = (findViewById(R.id.range)) as TextView
        val button: Button = (findViewById(R.id.buttonCalcularIMC)) as Button

        //evento button click o clicklistener
        button.setOnClickListener {
            var values = calcularIMC(etWeight.text.toString(), etHeight.text.toString())

            tvIMC.text = values[0].toString()
            tvRange.text = values[1].toString()

            when (values[2]) {
                0 -> tvRange.setBackgroundResource(R.color.colorBrown)
                1 -> tvRange.setBackgroundResource(R.color.colorGreen)
                2 -> tvRange.setBackgroundResource(R.color.colorGreenish)
                3 -> tvRange.setBackgroundResource(R.color.colorYellow)
                4 -> tvRange.setBackgroundResource(R.color.colorOrange)
                5 -> tvRange.setBackgroundResource(R.color.colorRed)
                else -> tvRange.setBackgroundResource(R.color.colorRed)
            }
        }
    }

    //función
    fun calcularIMC(weightString: String, heightString: String): List<Any>  {
        val imc: Double
        var range: String = ""
        val color: Int

        try {
            val weight: Double = weightString.toDouble()
            val height: Double = heightString.toDouble()

            if (weight <= 0 || height <= 0) {
                throw Exception()
            }

            imc = (weight / (height * height))

            when (imc) {
                in 0.0 .. 18.5 -> {
                    range = "Bajo peso"
                    color = 0
                }
                in 18.5 .. 24.9 -> {
                    range = "Peso normal"
                    color = 1
                }
                in 25.0 .. 29.9 -> {
                    range = "Sobrepeso"
                    color = 2
                }
                in 30.0 .. 34.9 -> {
                    range = "Obesidad grado 1"
                    color = 3
                }
                in 35.0 .. 39.9 -> {
                    range = "Obesidad grado 2"
                    color = 4
                }
                else -> {
                    range = "Obesidad grado 3"
                    color = 5
                }
            }
        } catch (e: Exception) {
            return listOf("Ingrese bien las cantidades", "Primero el peso en kilogramos y por último la estatura en metros con decimal", -1)
        }

        return listOf(String.format("%.1f", imc), range, color)
    }
}