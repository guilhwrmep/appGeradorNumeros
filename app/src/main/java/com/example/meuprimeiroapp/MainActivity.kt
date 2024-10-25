package com.example.meuprimeiroapp

import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.text_input)
        val textResult: TextView = findViewById(R.id.text_result)
        val btnGenerate: Button = findViewById(R.id.button)

        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val result = prefs.getString("result", null)

        result?.let {
            textResult.text = "Ultima aposta:  $it"      /* 1 forma if != null */
        }
        /*if (result != null) {
            textResult.text = "Ultima aposta: $result"   2º forma != null
        }*/


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

        /*val editor = prefs.edit()
        editor.putString("result", textResult.text.toString())      /// primeira forma de fazer
        editor.apply()*/

        prefs.edit().apply() {
            putString("result", textResult.text.toString())     /* segunda forma */
            apply()
        }

    }
}





