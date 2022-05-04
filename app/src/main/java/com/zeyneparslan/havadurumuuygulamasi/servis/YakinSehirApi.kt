package com.zeyneparslan.havadurumuuygulamasi.servis

import com.zeyneparslan.havadurumuuygulamasi.model.YakinSehir
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YakinSehirApi {

    // https://www.metaweather.com/api/location/search/?lattlong=36.96,-122.02

    @GET("api/location/search")
    fun getSehir(
        @Query("lattlong") latt_long : String
    ): Single<List<YakinSehir>>
}