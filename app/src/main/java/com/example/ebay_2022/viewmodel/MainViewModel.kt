package com.example.ebay_2022.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ebay_2022.api.ParsingImpl
import com.example.ebay_2022.data.Earthquake
import com.example.ebay_2022.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val dataRepository = Repository(ParsingImpl())
    private val earthquakeLiveData = MutableLiveData<List<Earthquake>>()
    val earthquakes: LiveData<List<Earthquake>> = earthquakeLiveData

    init {
        viewModelScope.launch {
            val earthquake = withContext(Dispatchers.IO) {
                dataRepository.getEarthquake()
            }
            earthquakeLiveData.value = earthquake
        }
    }
}