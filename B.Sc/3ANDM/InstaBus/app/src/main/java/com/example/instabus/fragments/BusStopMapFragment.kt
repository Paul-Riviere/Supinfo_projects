package com.example.instabus.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.instabus.DetailsActivity
import com.example.instabus.R
import com.example.instabus.data.BusStop
import com.example.instabus.services.SharedDataViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.Serializable

class BusStopMapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    override fun onInfoWindowClick(marker: Marker?) {
        Log.e("MARKER_CLICKED", marker?.title)
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("selectedStop", marker?.tag as Serializable)
        startActivity(intent)
    }

    private lateinit var model: SharedDataViewModel

    private var googleMap: GoogleMap?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layoutBusMap = inflater.inflate(R.layout.fragment_bus_stop_map, container, false)
        model = activity?.let { ViewModelProviders.of(it).get(SharedDataViewModel::class.java) }!!
        return layoutBusMap
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap=p0

        //Adding markers to map
        model.stopList.observe(viewLifecycleOwner, Observer {
            for (busStop in model.stopList.value!!) {
                val coords = LatLng(busStop.lat?.toDouble()!!, busStop.lon?.toDouble()!!)
                val markerOptions: MarkerOptions = MarkerOptions().position(coords).title(busStop.street_name)
                googleMap.let {
                    it!!.addMarker(markerOptions).run {
                        tag = busStop
                    }
                }
            }
        })
        val barcelone= LatLng(41.3985182,2.1917991)

        // moving camera and zoom map
        val zoomLevel = 12.0f //This goes up to 21

        googleMap.let {
            it?.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelone, zoomLevel))
        }

        googleMap?.setOnInfoWindowClickListener(this@BusStopMapFragment)
    }
}