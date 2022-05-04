package com.zeyneparslan.havadurumuuygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zeyneparslan.havadurumuuygulamasi.model.HavaDurumu
import com.zeyneparslan.havadurumuuygulamasi.model.HavaDurumuBase
import com.zeyneparslan.havadurumuuygulamasi.servis.HavaDurumuApi
import com.zeyneparslan.havadurumuuygulamasi.servis.HavaDurumuApiServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.time.LocalDateTime

class HavaDurumuListesiViewModel : ViewModel() {

    val havaDurumu = MutableLiveData<List<HavaDurumu>>()
    val havaDurumu2 = ArrayList<HavaDurumu>()
    val havaHataMesaji = MutableLiveData<Boolean>()
    val havayukleniyor = MutableLiveData<Boolean>()

    private val havaDurumuApiServis = HavaDurumuApiServis()
    private val disposable = CompositeDisposable()

    fun refreshData(woeid: String){
        verileriİnternettenAl(woeid)
    }

    fun verileriİnternettenAl(woeid : String){
        havayukleniyor.value= true
        disposable.add(
            havaDurumuApiServis.getDataHava(woeid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<HavaDurumuBase>(){
                    override fun onSuccess(t: HavaDurumuBase) {
                        //Başarılı olursak
                        havaDurumu2.addAll(0,t.consolidated_weather)
                        setHavaDurumu()
                        havaHataMesaji.value = false
                        havayukleniyor.value = false
                    }
                    override fun onError(e: Throwable) {
                        //Hata alırsak
                        havaHataMesaji.value = true
                        havayukleniyor.value = false
                        println(e.printStackTrace())
                    }
                })
        )
        val current = LocalDateTime.now()
        for (i in 6..7){
            val a=current.plusDays(i.toLong())
            disposable.add(
                havaDurumuApiServis.getHavaDurumuTarih(woeid,a)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<List<HavaDurumu>>(){
                        override fun onSuccess(t: List<HavaDurumu>) {
                            //Başarılı olursak
                            havaDurumu2.add(t[0])
                            setHavaDurumu()
                        }
                        override fun onError(e: Throwable) {
                            //Hata alırsak
                            havaHataMesaji.value = true
                            havayukleniyor.value = false
                            println(e.printStackTrace())
                        }
                    })
            )
        }
    }
    fun setHavaDurumu(){
        val siraliHavaDurumu=havaDurumu2.sortedBy { t->t.applicable_date }
        havaDurumu.value=siraliHavaDurumu
    }
}