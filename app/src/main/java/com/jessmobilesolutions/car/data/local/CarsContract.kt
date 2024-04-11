package com.jessmobilesolutions.car.data.local

import android.provider.BaseColumns

object CarsContract {
    
    const val DATABASE_NAME = "db_car"
    object CarEntry : BaseColumns {
        const val TABLE_NAME = "car"
        const val COLUMN_NAME_CAR_ID = "car_id"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_BATTERY = "battery"
        const val COLUMN_NAME_POWER = "power"
        const val COLUMN_NAME_TIME = "rechargeTime"
        const val COLUMN_NAME_PHOTO = "url_photo"
    }

    const val TABLE_CAR = "CREATE TABLE ${CarEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${CarEntry.COLUMN_NAME_CAR_ID} TEXT," +
            "${CarEntry.COLUMN_NAME_PRICE} TEXT," +
            "${CarEntry.COLUMN_NAME_BATTERY} TEXT," +
            "${CarEntry.COLUMN_NAME_POWER} TEXT," +
            "${CarEntry.COLUMN_NAME_TIME} TEXT," +
            "${CarEntry.COLUMN_NAME_PHOTO} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${CarEntry.TABLE_NAME}"
}