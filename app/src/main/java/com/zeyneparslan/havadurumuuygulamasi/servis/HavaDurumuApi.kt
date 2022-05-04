package com.zeyneparslan.havadurumuuygulamasi.servis

import com.zeyneparslan.havadurumuuygulamasi.model.HavaDurumu
import com.zeyneparslan.havadurumuuygulamasi.model.HavaDurumuBase
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.Month
import java.util.*

interface HavaDurumuApi {

    //https://www.metaweather.com/api/location/44418/

    // https://www.metaweather.com/api/location/44418/2013/4/27/

    @GET("api/location/{woeid}")

    fun getHavaDurumu(
        @Path("woeid") woeid : String
    ): Single<HavaDurumuBase>

    @GET("api/location/{woeid}/{year}/{month}/{day}")
    fun getHavaDurumuTarih(
        @Path("woeid") woeid: String,
        @Path("year") year: Int,
        @Path("day") day: Int,
        @Path("month") month: Int
    ):Single<List<HavaDurumu>>
}