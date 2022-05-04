package com.zeyneparslan.havadurumuuygulamasi.servis

import com.zeyneparslan.havadurumuuygulamasi.model.HavaDurumu
import com.zeyneparslan.havadurumuuygulamasi.model.HavaDurumuBase
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

class HavaDurumuApiServis {

    //https://www.metaweather.com/api/location/44418/

    // https://www.metaweather.com/api/location/44418/2013/4/27/

    private val BASE_URL = "https://www.metaweather.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(HavaDurumuApi::class.java)

    fun getDataHava(woeid : String) : Single<HavaDurumuBase>{
        return api.getHavaDurumu(woeid)
    }
    fun getHavaDurumuTarih(woeid: String,dateTime: LocalDateTime): Single<List<HavaDurumu>> {
        return api.getHavaDurumuTarih(woeid,dateTime.year,dateTime.dayOfMonth,dateTime.monthValue)
    }
}