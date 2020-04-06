package com.example.instabus.fragments

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.instabus.BarcelonaAPI
import com.example.instabus.DetailsActivity
import com.example.instabus.R
import com.example.instabus.data.BarceloneApiRes
import com.example.instabus.data.BusStop
import com.example.instabus.services.SharedDataViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.net.URL

class BusStopListFragment : Fragment() {
    //private var listebis = URL("http://barcelonaapi.marcpous.com/bus/nearstation/latlon/41.3985182/2.1917991/1.json").readText()
    private lateinit var model: SharedDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var layoutBusList = inflater.inflate(R.layout.fragment_bus_stop_list, container, false)
        model = activity?.let { ViewModelProviders.of(it).get(SharedDataViewModel::class.java) }!!
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
                    var responseList = ArrayList<BusStop>()
                    for (stop in it) {
                        responseList.add(stop)
                        Log.d("LOADED STOP : ",stop.street_name)
                        val textView =  TextView(activity)
                        textView.text = stop.street_name
                        textView.textSize = 30F
                        textView.setOnClickListener {
                            val intent = Intent(context, DetailsActivity::class.java)
                            intent.putExtra("selectedStop", stop as Serializable)
                            startActivity(intent)
                        }
                        linearLayoutStops?.addView(textView)
                    }
                    model.setStopList(responseList)
                }
            }
            override fun onFailure(call: Call<BarceloneApiRes>, t: Throwable) {
                Log.e("TAN", "Error : $t")
            }
        })


    }
}