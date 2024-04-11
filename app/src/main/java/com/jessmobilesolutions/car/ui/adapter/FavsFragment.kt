package com.jessmobilesolutions.car.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jessmobilesolutions.car.R
import com.jessmobilesolutions.car.data.local.CarRepository
import com.jessmobilesolutions.car.domain.Car

class FavsFragment : Fragment() {
    private lateinit var recycleView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        val repository = CarRepository(requireContext())
        val carList = repository.getAllCars()
        setupList(carList)
    }

    private fun setupView(view: View) {
        recycleView = view.findViewById(R.id.recycleViewFavs)
    }

    private fun setupList(list: List<Car>) {
        val carAdapter = CarAdapter(list, isFavoriteScreen = true)
        recycleView.apply {
            visibility = View.VISIBLE
            adapter = carAdapter
        }
    }

}