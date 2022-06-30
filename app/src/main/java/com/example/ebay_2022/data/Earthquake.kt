package com.example.ebay_2022.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "earthquake")
data class Earthquake(

    @PrimaryKey
    @SerializedName("datetime")
    val datetime: String,

    @SerializedName("depth")
    val depth: Double,

    @SerializedName("lng")
    val lng: Double,

    @SerializedName("src")
    val src: String,

    @SerializedName("eqid")
    val eqid: String,

    @SerializedName("magnitude")
    val magnitude: Double,

    @SerializedName("lat")
    val lat: Double,

)