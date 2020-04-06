package com.example.instabus.data

import java.io.Serializable

class BusStop: Serializable {
    var id: String? = null
    var street_name: String? = null
    var city: String? = null
    var utm_x: String? = null
    var utm_y: String? = null
    var lat: String? = null
    var lon: String? = null
    var furniture: String? = null
    var buses: String? = null
    var distance: String? = null
}

class BarceloneApiRes {
    var data: DataRes? = null
}

class DataRes {
    var nearstations: List<BusStop>? = null
}

