package com.jessmobilesolutions.car.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jessmobilesolutions.car.R
import com.jessmobilesolutions.car.domain.Car

class CarAdapter(private val cars: List<Car>, private val isFavoriteScreen: Boolean = false) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var carItemLister: (Car) -> Unit = {}
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
        if(isFavoriteScreen){
            holder.favorite.setImageResource(R.drawable.baseline_star_outline_24)
        }
        holder.favorite.setOnClickListener {
            val car = cars[position]
            carItemLister(car)
            setupFavorite(car, holder)
        }
    }

    private fun setupFavorite(
        car: Car,
        holder: ViewHolder
    ) {
        car.isFavorite = !car.isFavorite
        if (car.isFavorite) {
            holder.favorite.setImageResource(R.drawable.baseline_star_24)
        } else {
            holder.favorite.setImageResource(R.drawable.baseline_star_outline_24)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val battery: TextView
        val power: TextView
        val rechargeTime: TextView
        val favorite: ImageView

        init {
            price = view.findViewById(R.id.textViewPriceValue)
            battery = view.findViewById(R.id.textViewBatteryValue)
            power = view.findViewById(R.id.textViewPowerValue)
            rechargeTime = view.findViewById(R.id.textViewRechargeTimeValue)
            favorite = view.findViewById(R.id.imageViewFavorite)
        }
    }
}

