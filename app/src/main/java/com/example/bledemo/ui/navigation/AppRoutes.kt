package com.example.bledemo.ui.navigation

import android.net.Uri

object AppRoutes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val SCAN = "scan"
    const val MY_DEVICES = "my_devices"
    const val SETTINGS = "settings"
    const val LIVE_DATA = "live_data"

    const val DEVICE_DETAILS_BASE = "device_details"
    const val DEVICE_DETAILS = "device_details/{deviceAddress}"

    const val GATT_BASE = "gatt"
    const val GATT = "gatt/{deviceAddress}"

    fun deviceDetails(deviceAddress: String): String {
        return "$DEVICE_DETAILS_BASE/${Uri.encode(deviceAddress)}"
    }

    fun gatt(deviceAddress: String): String {
        return "$GATT_BASE/${Uri.encode(deviceAddress)}"
    }
}
