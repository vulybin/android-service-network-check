package com.example.myserviceexample

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

fun write_str(str:String){
    Log.e("TEST: ", str)
}

fun isInet(context: Context):Boolean {
    val cm = (activity as MainActivity).getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}