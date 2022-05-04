package com.zeyneparslan.havadurumuuygulamasi.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeyneparslan.havadurumuuygulamasi.R
import com.zeyneparslan.havadurumuuygulamasi.adapter.YakinSehirRecyclerAdapter
import com.zeyneparslan.havadurumuuygulamasi.viewmodel.YakinSehirListesiViewModel
import kotlinx.android.synthetic.main.activity_yakin_sehirler_listesi.*
import kotlinx.android.synthetic.main.yakin_sehir_recycler_row.*

class YakinSehirlerListesiActivity : AppCompatActivity() {

    private lateinit var viewModel: YakinSehirListesiViewModel
    private val recyclerYakinSehirAdapter = YakinSehirRecyclerAdapter(arrayListOf())

    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yakin_sehirler_listesi)

        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()
        viewModel = ViewModelProvider(this).get(YakinSehirListesiViewModel::class.java)

        var locationValue = intent.extras!!.get("latt_long").toString()
        var lattLong = GET.getString("latt_long", locationValue)

        viewModel.refreshData(lattLong!!)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerYakinSehirAdapter

        swipeRefreshLayout.setOnRefreshListener {
            yakinSehirHataMesaji.visibility = View.VISIBLE
            yakinSehirYukleniyor.visibility = View.GONE
            recyclerView.visibility = View.GONE

            var latt_long = GET.getString("latt_long",lattLong)
            recyclerViewCityText.setText(latt_long)
            viewModel.refreshData(latt_long!!)
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.yakinSehirler.observe(this, Observer {yakinSehirler ->
            yakinSehirler?.let {
                recyclerView.visibility = View.VISIBLE
                recyclerYakinSehirAdapter.yakinSehirListesiniGuncelle(yakinSehirler)
            }
        })
        viewModel.yakinSehirHataMesaji.observe(this, Observer { hata ->
            hata?.let {
                if (it){
                    recyclerView.visibility = View.VISIBLE
                    yakinSehirHataMesaji.visibility = View.VISIBLE
                }else{
                    yakinSehirHataMesaji.visibility = View.GONE
                }
            }
        })
        viewModel.yakinSehirYukleniyor.observe(this, Observer { yukle ->
            yukle?.let {
                if(it){
                    recyclerView.visibility = View.GONE
                    yakinSehirHataMesaji.visibility = View.GONE
                    yakinSehirYukleniyor.visibility = View.VISIBLE
                }else{
                    yakinSehirYukleniyor.visibility = View.GONE
                }
            }
        })
    }
}