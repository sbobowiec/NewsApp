package com.bobowiec.newsapp.extensions

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.bobowiec.newsapp.App
import com.bobowiec.newsapp.injection.ApplicationComponent

fun Context.getAppComponent(): ApplicationComponent = (this.applicationContext as App).component

inline fun <reified T : Parcelable> createParcel(
    crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
    object : Parcelable.Creator<T> {
      override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
      override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
    }