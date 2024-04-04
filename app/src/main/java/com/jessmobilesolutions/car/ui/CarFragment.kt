package com.jessmobilesolutions.car.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jessmobilesolutions.car.R
import com.jessmobilesolutions.car.data.CarApi
import com.jessmobilesolutions.car.domain.Car
import com.jessmobilesolutions.car.ui.adapter.CarAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_STRING = "https://jessicalves.github.io/server/cars-api/"

class CarFragment : Fragment() {

    private lateinit var floatActionButtonCalculator: FloatingActionButton
    private lateinit var recycleView: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var imageWifi: ImageView
    private lateinit var textViewWifi: TextView
    private lateinit var carApi: CarApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupView(view)
    }

    private fun setupRetrofit() {
        val retrofit =
            Retrofit.Builder().baseUrl(URL_STRING).addConverterFactory(GsonConverterFactory.create()).build()

        carApi = retrofit.create(CarApi::class.java)
    }

    private fun getAllCars() {
        carApi.getAllCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        setupList(it)
                    }
                    progress.isVisible = false
                    imageWifi.isVisible = false
                    textViewWifi.isVisible = false
                } else {
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<List<Car>>, p1: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        setupListeners()
        if (checkForInternet(context)) {
            getAllCars()
        } else {
            emptyState()
        }
    }

    private fun emptyState() {
        progress.isVisible = false
        recycleView.isVisible = false
        imageWifi.isVisible = true
        textViewWifi.isVisible = true
    }

    private fun setupView(view: View) {
        floatActionButtonCalculator = view.findViewById(R.id.floatActionButtonCalculator)
        recycleView = view.findViewById(R.id.recycleViewCars)
        progress = view.findViewById(R.id.progressBar)
        imageWifi = view.findViewById(R.id.imageView)
        textViewWifi = view.findViewById(R.id.textViewWifi)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    private fun setupList(list: List<Car>) {
        val carAdapter = CarAdapter(list)
        recycleView.apply {
            visibility = View.VISIBLE
            adapter = carAdapter
        }
    }

    private fun setupListeners() {
        floatActionButtonCalculator.setOnClickListener {
            startActivity(Intent(context, CalculatorActivity::class.java))
        }
    }

    private fun checkForInternet(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activityNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activityNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activityNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}