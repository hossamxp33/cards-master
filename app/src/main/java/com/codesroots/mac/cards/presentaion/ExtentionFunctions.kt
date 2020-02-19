package com.codesroots.mac.cards.presentaion

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codesroots.mac.cards.R

import com.google.android.material.snackbar.Snackbar
import java.math.RoundingMode


fun Any.toast(context: Context) {
    Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).show()
}

fun Any.snack(view: View, length: Int= Snackbar.LENGTH_SHORT ) {

    Snackbar.make(view ,this.toString(),length ).show()

}
fun Double.round() :Double{
   return this.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
}


fun isInternetConnectionAvailable(context: Context): Boolean {
    try {
        val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        return (conMgr.activeNetworkInfo != null
                && conMgr.activeNetworkInfo.isAvailable
                && conMgr.activeNetworkInfo.isConnected )

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return false
}

fun Int.length() = when(this) {
    0 -> 1
    else -> Math.log10(Math.abs(toDouble())).toInt() + 1
}


fun loudImage(context: Context, imag: ImageView, url: String?) {
    Glide.with(context).applyDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.noimg)
            .error(R.drawable.noimg)).load(url).into(imag)
}












