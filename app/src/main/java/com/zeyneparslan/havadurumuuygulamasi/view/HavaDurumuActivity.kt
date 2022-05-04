package com.zeyneparslan.havadurumuuygulamasi.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeyneparslan.havadurumuuygulamasi.R
import com.zeyneparslan.havadurumuuygulamasi.adapter.HavaDurumuRecyclerAdapter
import com.zeyneparslan.havadurumuuygulamasi.model.HavaDurumu
import com.zeyneparslan.havadurumuuygulamasi.viewmodel.HavaDurumuListesiViewModel
import kotlinx.android.synthetic.main.activity_hava_durumu.*

class HavaDurumuActivity : AppCompatActivity() {

    private lateinit var viewModel: HavaDurumuListesiViewModel
    private val recyclerHavaAdapter = HavaDurumuRecyclerAdapter(arrayListOf())

    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hava_durumu)

        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()

        println("Burası hava durumu aktivitesidir")
        intent.getStringExtra("woeid")

        viewModel = ViewModelProvider(this).get(HavaDurumuListesiViewModel::class.java)

        var id = intent.extras!!.get("woeid").toString()
        var woeid = GET.getString("woeid",id)

        viewModel. refreshData(woeid!!)

        recyclerView2.layoutManager = LinearLayoutManager(this)
        recyclerView2.adapter = recyclerHavaAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.havaDurumu.observe(this, Observer { hava->
            hava?.let {
                recyclerView2.visibility = View.VISIBLE
                recyclerHavaAdapter.HavaDurumuListesiniGuncelle(hava)
            }
        })
        viewModel.havaHataMesaji.observe(this, Observer { hata->
            hata?.let {
                if (it){
                    recyclerView2.visibility = View.GONE
                    havaDurumuHataMesaji.visibility = View.VISIBLE
                }else{
                    havaDurumuHataMesaji.visibility = View.GONE
                }
            }
        })
        viewModel.havayukleniyor.observe(this, Observer { yukle->
            yukle?.let {
                if(it){
                    recyclerView2.visibility = View.GONE
                    havaDurumuHataMesaji.visibility = View.GONE
                    havaDurumuYukleniyor.visibility = View.VISIBLE
                }else{
                    havaDurumuYukleniyor.visibility = View.GONE
                }
            }
        })
    }
}