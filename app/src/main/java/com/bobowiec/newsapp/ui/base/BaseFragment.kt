package com.bobowiec.newsapp.ui.base

import android.support.annotation.StringRes
import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {

  @StringRes abstract fun getTitle(): Int

  abstract fun getFragmentTag(): String

}