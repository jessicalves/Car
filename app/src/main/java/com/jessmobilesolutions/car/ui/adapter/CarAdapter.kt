package com.jessmobilesolutions.car.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jessmobilesolutions.car.R
import com.jessmobilesolutions.car.domain.Car

class CarAdapter(private val cars: List<Car>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.price.text = cars[position].price
        holder.battery.text = cars[position].battery
        holder.power.text = cars[position].power
        holder.rechargeTime.text = cars[position].rechargeTime
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val battery: TextView
        val power: TextView
        val rechargeTime: TextView

        init {
            price = view.findViewById(R.id.textViewPriceValue)
            battery = view.findViewById(R.id.textViewBatteryValue)
            power = view.findViewById(R.id.textViewPowerValue)
            rechargeTime = view.findViewById(R.id.textViewRechargeTimeValue)
        }
    }
}

