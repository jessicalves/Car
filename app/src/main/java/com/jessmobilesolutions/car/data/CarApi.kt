package com.jessmobilesolutions.car.data

import com.jessmobilesolutions.car.domain.Car
import retrofit2.Call
import retrofit2.http.GET

interface CarApi {
    @GET("cars.json")
    fun getAllCars() : Call<List<Car>>
}