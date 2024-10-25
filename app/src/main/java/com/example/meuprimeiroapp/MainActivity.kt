package com.example.meuprimeiroapp

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.util.Log.*
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editText: EditText = findViewById(R.id.text_input)
        val textResult: TextView = findViewById(R.id.text_result)
        val btnGenerate: Button = findViewById(R.id.button)

        btnGenerate.setOnClickListener {


            val text = editText.text.toString()
            numberGenerator(text, textResult)
        }
    }

    private fun numberGenerator(text: String, textResult: TextView) {
        if (text.isEmpty()) {
            Toast.makeText(this, "informe um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val qtd = text.toInt()

        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, "informe um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val numbers = mutableSetOf<Int>()
        val random = Random()

        while (true) {
            val number = random.nextInt(60)
            numbers.add(number + 1)

            if (numbers.size == qtd) {
                break
            }
        }

        textResult.text = numbers.joinToString(" - ")

    }
}





