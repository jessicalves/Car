package com.jessmobilesolutions.car.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.jessmobilesolutions.car.R
import com.jessmobilesolutions.car.data.CarFactory
import com.jessmobilesolutions.car.ui.adapter.CarAdapter

class MainActivity : AppCompatActivity() {
    lateinit var btnCalculator: Button
    lateinit var recycleView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        setupListeners()
        setupList()
    }

    private fun setupView() {
        btnCalculator = findViewById(R.id.buttonCalculator)
        recycleView = findViewById(R.id.recycleViewCars)
    }

    private fun setupList() {
        val adapter = CarAdapter(CarFactory.list)
        recycleView.adapter = adapter
    }

    private fun setupListeners() {
        btnCalculator.setOnClickListener() {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }
    }
}