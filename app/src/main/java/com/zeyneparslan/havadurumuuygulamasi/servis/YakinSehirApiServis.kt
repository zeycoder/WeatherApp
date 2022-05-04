package com.zeyneparslan.havadurumuuygulamasi.servis

import com.zeyneparslan.havadurumuuygulamasi.model.YakinSehir
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class YakinSehirApiServis {

    // https://www.metaweather.com/api/location/search/?lattlong=36.96,-122.02

    private val BASE_URL = "https://www.metaweather.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(YakinSehirApi::class.java)

    fun getData(latt_long : String) : Single<List<YakinSehir>> {
        return api.getSehir(latt_long)
    }
}