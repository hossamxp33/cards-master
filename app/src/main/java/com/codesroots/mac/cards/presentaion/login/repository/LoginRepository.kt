package com.codesroots.hossam.mandoobapp.presentation.login.repository


import android.util.Log
import androidx.core.util.Consumer
import androidx.lifecycle.MutableLiveData
import com.codesroots.mac.cards.models.LogData
import com.codesroots.mac.firstkotlon.DataLayer.ApiService.APIServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class LoginRepository {





    fun Login(username:String,password:String,livedata: MutableLiveData<LogData>?) {

        APIServices.create().Login(username,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->

                }
            )
    }



}
