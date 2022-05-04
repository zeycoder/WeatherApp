package com.zeyneparslan.havadurumuuygulamasi.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.LinktenResimGetir(url : String?){
    Glide.with(context).load(url).into(this)
}