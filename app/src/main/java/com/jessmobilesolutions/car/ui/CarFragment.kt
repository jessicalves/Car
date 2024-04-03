package com.jessmobilesolutions.car.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jessmobilesolutions.car.R
import com.jessmobilesolutions.car.data.CarFactory
import com.jessmobilesolutions.car.ui.adapter.CarAdapter

class CarFragment : Fragment() {

    private lateinit var floatActionButtonCalculator: FloatingActionButton
    private lateinit var recycleView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatActionButtonCalculator = view.findViewById(R.id.floatActionButtonCalculator)
        recycleView = view.findViewById(R.id.recycleViewCars)
        setupList()
        setupListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    private fun setupList() {
        val adapter = CarAdapter(CarFactory.list)
        recycleView.adapter = adapter
    }

    private fun setupListeners() {
        floatActionButtonCalculator.setOnClickListener() {
            startActivity(Intent(context, CalculatorActivity::class.java))
        }
    }

}