package com.zeyneparslan.havadurumuuygulamasi.model

import com.google.gson.annotations.SerializedName

data class HavaDurumu(
    @SerializedName("woeid")
    val woeid : String?,
    @SerializedName("weather_state_name")
    val weather_state_name : String?,
    @SerializedName("weather_state_abbr")
    val weather_state_abbr : String?,
    @SerializedName("applicable_date")
    val applicable_date : String?,
    @SerializedName("the_temp")
    val the_temp : Double?,
    @SerializedName("id")
    val id :Long?
){
}