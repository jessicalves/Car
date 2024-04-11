package com.jessmobilesolutions.car.data

import com.jessmobilesolutions.car.domain.Car

object CarFactory {
    val list = listOf<Car>(
        Car(
            id = 1,
            price = "R$ 75.000,00",
            battery = "300 kWh",
            power = "200cv",
            rechargeTime = "2h",
            urlPhoto = "",
            isFavorite = false
        ),
        Car(
            id = 2,
            price = "R$ 85.000,00",
            battery = "500 kWh",
            power = "400cv",
            rechargeTime = "3h",
            urlPhoto = "",
            isFavorite = false
        )
    )
}