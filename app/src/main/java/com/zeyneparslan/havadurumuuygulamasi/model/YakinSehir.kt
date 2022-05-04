package com.zeyneparslan.havadurumuuygulamasi.model

import com.google.gson.annotations.SerializedName

data class YakinSehir(
    @SerializedName("woeid")
    val woeid : String?,
    @SerializedName("title")
    val title : String?,
    @SerializedName("distance")
    val distance : Int?,
    @SerializedName("latt_long")
    val latt_long : String?
){
}