package com.example.ebay_2022.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ebay_2022.adapter.EarthquakeAdapter
import com.example.ebay_2022.databinding.ActivityMainBinding
import com.example.ebay_2022.util.Extender
import com.example.ebay_2022.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), EarthquakeAdapter.OnItemClickListener {

    override fun onItemClick(position: Int) {
        Toast.makeText(
            this@MainActivity,
            "Clicked Employee at position $position",
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        var thisVal = 1                 // 1 = 'online-status' && 2 = 'offline'..
    }

    // view-model
    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val earthquakesAdapter = EarthquakeAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = earthquakesAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            // ONLINE
            if (Extender.isOnline(applicationContext)) {
                progress_bar.isVisible = true
                thisVal = 1
                viewModel.earthquakes.observe(this@MainActivity) { employeesBeforeSwipe ->
                    earthquakesAdapter.submitList(employeesBeforeSwipe)
                    progressBar.isVisible = false
                }

                // OFFLINE
            } else {
                thisVal = 2
                viewModel.earthquakes.observe(this@MainActivity) { employees ->
                    earthquakesAdapter.submitList(employees)
                    Toast.makeText(                                 // to notify the user that network is offline..
                        applicationContext,
                        "Network is offline... ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // nullify the adapter when activity is destroyed
    // to free up memory resources (otherwise -> memory leak)
    override fun onDestroy() {
        super.onDestroy()
        recycler_View.adapter = null
    }
}