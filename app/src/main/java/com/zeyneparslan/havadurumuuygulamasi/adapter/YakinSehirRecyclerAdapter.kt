package com.zeyneparslan.havadurumuuygulamasi.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeyneparslan.havadurumuuygulamasi.R
import com.zeyneparslan.havadurumuuygulamasi.model.YakinSehir
import com.zeyneparslan.havadurumuuygulamasi.view.HavaDurumuActivity
import com.zeyneparslan.havadurumuuygulamasi.viewmodel.YakinSehirListesiViewModel
import kotlinx.android.synthetic.main.yakin_sehir_recycler_row.view.*

class YakinSehirRecyclerAdapter ( val yakinSehirListesi : ArrayList<YakinSehir>) : RecyclerView.Adapter<YakinSehirRecyclerAdapter.YakinSehirViewHolder>() {
    class YakinSehirViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YakinSehirViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.yakin_sehir_recycler_row,parent,false)
        return YakinSehirViewHolder(view)
    }

    override fun onBindViewHolder(holder: YakinSehirViewHolder, position: Int) {
        holder.itemView.recyclerViewCityText.text = (yakinSehirListesi.get(position).title+" hava durumu")
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,HavaDurumuActivity::class.java)
            intent.putExtra("woeid",yakinSehirListesi.get(position).woeid)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return yakinSehirListesi.size
    }

    fun yakinSehirListesiniGuncelle(yeniYakinSehirListesi:List<YakinSehir>){
        yakinSehirListesi.clear()
        yakinSehirListesi.addAll(yeniYakinSehirListesi)
        notifyDataSetChanged()
    }
}