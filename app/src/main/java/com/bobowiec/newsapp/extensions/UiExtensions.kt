package com.bobowiec.newsapp.extensions

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bobowiec.newsapp.R
import com.squareup.picasso.Picasso

fun View.show() {
  visibility = View.VISIBLE
}

fun View.hide() {
  visibility = View.GONE
}

fun MenuItem.show() {
  isVisible = true
}

fun MenuItem.hide() {
  isVisible = false
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl: String?) {
  if (TextUtils.isEmpty(imageUrl)) {
    Picasso.with(context)
        .load(R.mipmap.ic_launcher)
        .into(this)
  } else {
    Picasso.with(context)
        .load(imageUrl)
        .into(this)
  }
}