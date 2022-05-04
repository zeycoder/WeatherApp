package com.zeyneparslan.havadurumuuygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zeyneparslan.havadurumuuygulamasi.model.YakinSehir
import com.zeyneparslan.havadurumuuygulamasi.servis.YakinSehirApiServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class YakinSehirListesiViewModel : ViewModel() {

    val yakinSehirler = MutableLiveData<List<YakinSehir>>()
    val yakinSehirHataMesaji = MutableLiveData<Boolean>()
    val yakinSehirYukleniyor = MutableLiveData<Boolean>()

    private val yakinSehirApiServis = YakinSehirApiServis()
    private val disposable = CompositeDisposable()

    fun refreshData(latt_long: String){
        verileriInternettenAl(latt_long)
    }
    private fun verileriInternettenAl(latt_long: String){
        yakinSehirYukleniyor.value=true
        disposable.add(
            yakinSehirApiServis.getData(latt_long)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<YakinSehir>>(){
                    override fun onSuccess(t: List<YakinSehir>) {
                        //Başarılı olursak
                        yakinSehirler.value = t
                        yakinSehirHataMesaji.value = false
                        yakinSehirYukleniyor.value = false
                    }
                    override fun onError(e: Throwable) {
                        //Hata alırsak
                        yakinSehirHataMesaji.value = true
                        yakinSehirYukleniyor.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
}