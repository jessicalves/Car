package com.jessmobilesolutions.car.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import com.jessmobilesolutions.car.data.local.CarsContract.CarEntry.COLUMN_NAME_BATTERY
import com.jessmobilesolutions.car.data.local.CarsContract.CarEntry.COLUMN_NAME_CAR_ID
import com.jessmobilesolutions.car.data.local.CarsContract.CarEntry.COLUMN_NAME_PHOTO
import com.jessmobilesolutions.car.data.local.CarsContract.CarEntry.COLUMN_NAME_POWER
import com.jessmobilesolutions.car.data.local.CarsContract.CarEntry.COLUMN_NAME_PRICE
import com.jessmobilesolutions.car.data.local.CarsContract.CarEntry.COLUMN_NAME_TIME
import com.jessmobilesolutions.car.domain.Car
import java.lang.Exception

class CarRepository(private val context: Context) {
    private fun save(car: Car): Boolean {
        var isSaved = false
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(CarsContract.CarEntry.COLUMN_NAME_CAR_ID, car.id)
                put(CarsContract.CarEntry.COLUMN_NAME_PRICE, car.price)
                put(CarsContract.CarEntry.COLUMN_NAME_BATTERY, car.battery)
                put(CarsContract.CarEntry.COLUMN_NAME_POWER, car.power)
                put(CarsContract.CarEntry.COLUMN_NAME_TIME, car.rechargeTime)
                put(CarsContract.CarEntry.COLUMN_NAME_PHOTO, car.urlPhoto)
            }

            var inserted = db?.insert(CarsContract.CarEntry.TABLE_NAME, null, values)
            if (inserted != null)
                isSaved = true

        } catch (ex: Exception) {
            ex.message?.let { Log.e("Error of insert", it) }
        }

        return isSaved
    }

    private fun findCadById(id: Int): Car {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRICE,
            COLUMN_NAME_BATTERY,
            COLUMN_NAME_POWER,
            COLUMN_NAME_TIME,
            COLUMN_NAME_PHOTO
        )

        val filter = "$COLUMN_NAME_CAR_ID = ?"
        val filterValues = arrayOf(id.toString())
        val cursor = db.query(
            CarsContract.CarEntry.TABLE_NAME,
            columns, filter, filterValues, null, null, null
        )

        var itemID: Long = 0
        var itemPrice: String = ""
        var itemBattery: String = ""
        var itemPower: String = ""
        var itemTime: String = ""
        var itemPhoto: String = ""

        with(cursor) {
            while (moveToNext()) {
                itemID = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                itemPrice = getString(getColumnIndexOrThrow(COLUMN_NAME_PRICE))
                itemBattery = getString(getColumnIndexOrThrow(COLUMN_NAME_BATTERY))
                itemPower = getString(getColumnIndexOrThrow(COLUMN_NAME_POWER))
                itemTime = getString(getColumnIndexOrThrow(COLUMN_NAME_TIME))
                itemPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_PHOTO))
            }
        }

        cursor.close()

        return Car(
            id = itemID.toInt(),
            price = itemPrice,
            battery = itemBattery,
            power = itemPower,
            rechargeTime = itemTime,
            urlPhoto = itemPhoto,
            isFavorite = true
        )
    }

    fun saveIfNotExist(car: Car) {
        val car = findCadById(car.id)
        if (car.id == ID_WHEN_NO_CAR) {
            save(car)
        }

    }

    fun getAllCars(): List<Car> {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRICE,
            COLUMN_NAME_BATTERY,
            COLUMN_NAME_POWER,
            COLUMN_NAME_TIME,
            COLUMN_NAME_PHOTO
        )

        val cursor = db.query(
            CarsContract.CarEntry.TABLE_NAME,
            columns, null, null, null, null, null
        )

        val cars = mutableListOf<Car>()

        with(cursor) {
            while (moveToNext()) {
                val itemID = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                val itemPrice = getString(getColumnIndexOrThrow(COLUMN_NAME_PRICE))
                val itemBattery = getString(getColumnIndexOrThrow(COLUMN_NAME_BATTERY))
                val itemPower = getString(getColumnIndexOrThrow(COLUMN_NAME_POWER))
                val itemTime = getString(getColumnIndexOrThrow(COLUMN_NAME_TIME))
                val itemPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_PHOTO))
                cars.add(
                    Car(
                        id = itemID.toInt(),
                        price = itemPrice,
                        battery = itemBattery,
                        power = itemPower,
                        rechargeTime = itemTime,
                        urlPhoto = itemPhoto,
                        isFavorite = true
                    )
                )
            }
        }

        cursor.close()
        return cars
    }

    companion object {
        const val ID_WHEN_NO_CAR = 0
    }
}