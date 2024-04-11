package com.jessmobilesolutions.car.ui

import android.content.Context
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
    private lateinit var buttonCalculatorAuto: Button
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
        cacheResult()
    }

    private fun cacheResult() {
        val calculatedValue = getSharedPref()
        result.text = calculatedValue.toString()
    }

    private fun setupView() {
        price = findViewById(R.id.editTextPriceKwh)
        km = findViewById(R.id.editTextKmPrecorrido)
        result = findViewById(R.id.textViewResult)
        buttonCalculatorAuto = findViewById(R.id.buttonCalculatorAuto)
        closeView = findViewById(R.id.imageViewClose)
    }

    private fun calculate() {
        val price = price.text.toString().toFloat()
        val km = km.text.toString().toFloat()
        val cal = price / km
        result.text = cal.toString()
        saveSharedPref(cal)
    }

    private fun setupListeners() {
        buttonCalculatorAuto.setOnClickListener() {
            calculate()
        }
        closeView.setOnClickListener() {
            finish()
        }
    }

    private fun saveSharedPref(value: Float) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putFloat(getString(R.string.saved_calc), value)
            apply()
        }
    }

    private fun getSharedPref(): Float {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return 0.0f
        return sharedPref.getFloat(getString(R.string.saved_calc), 0.0f)
    }
}