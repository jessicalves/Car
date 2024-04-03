package com.jessmobilesolutions.car.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jessmobilesolutions.car.R

class CalculatorActivity : AppCompatActivity() {
    private lateinit var price: EditText
    private lateinit var km: EditText
    private lateinit var btnCalculator: Button
    private lateinit var result: TextView
    private lateinit var closeView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        setupListeners()
    }

    private fun setupView() {
        price = findViewById(R.id.editTextPriceKwh)
        km = findViewById(R.id.editTextKmPrecorrido)
        result = findViewById(R.id.textViewResult)
        btnCalculator = findViewById(R.id.buttonCalculatorAuto)
        closeView = findViewById(R.id.imageViewClose)
    }

    private fun calculator() {
        val price = price.text.toString().toFloat()
        val km = km.text.toString().toFloat()
        val cal = price / km
        result.text = cal.toString()
    }

    private fun setupListeners() {
        btnCalculator.setOnClickListener() {
            calculator()
        }
        closeView.setOnClickListener() {
            finish()
        }
    }
}