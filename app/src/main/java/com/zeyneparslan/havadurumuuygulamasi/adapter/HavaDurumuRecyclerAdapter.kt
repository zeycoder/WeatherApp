package com.zeyneparslan.havadurumuuygulamasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.zeyneparslan.havadurumuuygulamasi.R
import com.zeyneparslan.havadurumuuygulamasi.model.HavaDurumu
import com.zeyneparslan.havadurumuuygulamasi.model.YakinSehir
import com.zeyneparslan.havadurumuuygulamasi.util.LinktenResimGetir
import com.zeyneparslan.havadurumuuygulamasi.viewmodel.HavaDurumuListesiViewModel
import kotlinx.android.synthetic.main.hava_durumu_recycler_row.view.*
import kotlin.math.roundToInt

class HavaDurumuRecyclerAdapter(val havaDurumuListesi : ArrayList<HavaDurumu>) : RecyclerView.Adapter<HavaDurumuRecyclerAdapter.HavaDurumuViewHolder>() {
    class HavaDurumuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HavaDurumuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.hava_durumu_recycler_row,parent,false)
        return HavaDurumuViewHolder(view)
    }

    override fun onBindViewHolder(holder: HavaDurumuViewHolder, position: Int) {
        val havaDurumu=havaDurumuListesi.get(position)
        holder.itemView.recyclerViewWeatherText.text = (havaDurumu.the_temp!!.roundToInt().toString()+" °C")
        holder.itemView.recyclerViewDateText.text = havaDurumu.applicable_date
        holder.itemView.recyclerViewImageView.LinktenResimGetir("https://www.metaweather.com/static/img/weather/ico/${havaDurumu.weather_state_abbr}.ico")
    }

    override fun getItemCount(): Int {
        return havaDurumuListesi.size
    }

    fun HavaDurumuListesiniGuncelle(yeniHavaDurumuListesi:List<HavaDurumu>) {
        havaDurumuListesi.clear()
        havaDurumuListesi.addAll(yeniHavaDurumuListesi)
        notifyDataSetChanged()
    }
}
