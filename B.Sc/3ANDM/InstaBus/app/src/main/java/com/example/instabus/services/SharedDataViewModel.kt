package com.example.instabus.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instabus.data.BusStop

class SharedDataViewModel: ViewModel() {
    var stopList: MutableLiveData<List<BusStop>> = MutableLiveData()
    var selectedStop: MutableLiveData<BusStop> = MutableLiveData()

    fun getStopList(): List<BusStop>? {
        return stopList.value
    }

    fun setStopList(newStopList: List<BusStop>) {
        stopList.value = newStopList
    }

    fun getSelectedStop(): List<BusStop>? {
        return stopList.value
    }

    fun setSelectedStop(newSelectedStop: BusStop) {
        selectedStop.value = newSelectedStop
    }
}