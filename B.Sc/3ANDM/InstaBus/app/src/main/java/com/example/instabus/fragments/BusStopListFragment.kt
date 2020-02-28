package com.example.instabus.fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.instabus.BarcelonaAPI
import com.example.instabus.R
import com.example.instabus.data.BarceloneApiRes
import com.example.instabus.data.BusStop
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class BusStopListFragment : Fragment() {

    private var liste = arrayOf("station 1", "station 2", "station 3", "station 4", "station 5", "station 6", "station 7", "station 8",
        "station 9", "station 10", "station 11", "station 12", "station 13", "station 14", "station 15", "station 16",
        "station 17", "station 18", "station 19", "station 20", "station 21", "station 22", "station 23", "station 24",
        "station 25", "station 26", "station 27", "station 28", "station 29", "station 30", "station 31", "station 32")

    //private var listebis = URL("http://barcelonaapi.marcpous.com/bus/nearstation/latlon/41.3985182/2.1917991/1.json").readText()
    private var listeArrets = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var layoutBusList = inflater.inflate(R.layout.fragment_bus_stop_list, container, false)
        return layoutBusList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val linearLayoutStops = view?.findViewById<LinearLayout>(R.id.busStopListLayout)

        val tanUrl = "http://barcelonaapi.marcpous.com/"

        val retrofit = Retrofit.Builder()
            .baseUrl(tanUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(BarcelonaAPI::class.java)
        val tanStops = service.getStops()

        tanStops.enqueue(object: Callback<BarceloneApiRes> {
            override fun onResponse(call: Call<BarceloneApiRes>, response: Response<BarceloneApiRes>) {
                response.body()?.data?.nearstations?.let {
                    for (stop in it) {
                        Log.e("STOPS",stop.street_name)
                        val textView =  TextView(activity)
                        textView.text = stop.street_name
                        textView.textSize = 30F
                        textView.gravity = Gravity.LEFT
//            textView.setClickable(true)
                        linearLayoutStops?.addView(textView)                    }
                }
            }
            override fun onFailure(call: Call<BarceloneApiRes>, t: Throwable) {
                Log.e("TAN", "Error : $t")
            }
        })


    }
}