package com.example.ebay_2022.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ebay_2022.R
import com.example.ebay_2022.util.Extender
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity() {

    companion object {
        var thisSelectedName = ""
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()

                // ONLINE
                if (Extender.isOnline(applicationContext)) {
                    if (selectedItem == getString(R.string.all)) {
                        thisSelectedName = getString(R.string.all)
                        val intentOne = Intent(this@LaunchActivity, MainActivity::class.java)
                        startActivity(intentOne)
                    }

                    if (selectedItem == getString(R.string.serious)) {
                        thisSelectedName = getString(R.string.serious)
                        val intentTwo = Intent(this@LaunchActivity, MainActivity::class.java)
                        startActivity(intentTwo)
                    }

                } else {
                    Toast.makeText(applicationContext, "Check your network", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    override fun onResume() {
        super.onResume()
        val employeesTypes = resources.getStringArray(R.array.earthquakes_types)
        val arrayAdapter = ArrayAdapter(this@LaunchActivity, R.layout.dropdown_item, employeesTypes)
        autoCompleteTextView.setAdapter(arrayAdapter)
    }
}