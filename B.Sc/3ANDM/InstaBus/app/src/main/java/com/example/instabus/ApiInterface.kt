package com.example.instabus

import com.example.instabus.data.BarceloneApiRes
import com.example.instabus.data.BusStop
import retrofit2.Call
import retrofit2.http.GET

interface BarcelonaAPI {
    @GET("bus/nearstation/latlon/41.3985182/2.1917991/1.json")
    fun getStops(): Call<BarceloneApiRes>
}
