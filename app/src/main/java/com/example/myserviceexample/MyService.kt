package com.example.myserviceexample

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    val TAG = "My Service"

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        showLog("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showLog("onStartCommand")

        val runnable = Runnable {
            for(i in 1..100){
                val flag = isInet(this)
                showLog("Service doing something. " +i.toString()+" "+ flag.toString())
                //write_str(i.toString())
                Thread.sleep(1000)
            }
            stopSelf()
        }

        val thread = Thread(runnable)
        thread.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        showLog("onDestroy")
        super.onDestroy()
    }

    fun showLog(message: String){
        Log.d(TAG, message)
    }

    fun isInet(context: Context):Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}
