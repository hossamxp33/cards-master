package com.codesroots.mac.firstkotlon.DataLayer.Repo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.models.*
import com.codesroots.mac.firstkotlon.DataLayer.ApiService.APIServices

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class  DataRepo {


    @SuppressLint("CheckResult")


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

    @SuppressLint("CheckResult")
    fun GetData(livedata: MutableLiveData<List<CompanyDatum>>?) {

        APIServices.create().GetCompanyData(PreferenceHelper.getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }

            .subscribe(
                { success ->
                    livedata?.postValue(success)
                },
                { error ->

                }
            )
    }


    @SuppressLint("CheckResult")

    fun GetPackageDetails(id:String,livedata: MutableLiveData<List<CompanyDatum>>?) {

        APIServices.create().GetPackageDetails(id,PreferenceHelper.getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { success ->
                    livedata?.postValue(success)
                },
                { error ->

                }
            )
    }

    @SuppressLint("CheckResult")

    fun BuyPackage(id:String,auth:String,amount:String,livedata: MutableLiveData<Buypackge>?,compiste: CompositeDisposable) {

        compiste .add(   APIServices.create().BuyPackage(id,auth,amount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->
                    print(error)
                }
            ))
    }
    @SuppressLint("CheckResult")

    fun GetMyBalance(livedata: MutableLiveData<MyBalance>?) {

        APIServices.create().GetMyBlanceData(PreferenceHelper.getToken())
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
    @SuppressLint("CheckResult")
    fun GetMyDeialyReport(auth:String,livedata: MutableLiveData<List<ReportDaily>>?) {

        APIServices.create().GetMyDeialyReport(auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->
                    print(error)
                }
            )
    }
    @SuppressLint("CheckResult")
    fun GetMyMonthReport(auth:String,from:String,to:String,livedata: MutableLiveData<List<ReportDaily>>?) {

        APIServices.create().GetMyDeialyReport(auth,from+","+to)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->
                    print(error)
                }
            )
    }

    @SuppressLint("CheckResult")
    fun GetTermsData(livedata: MutableLiveData<Terms>?) {

        APIServices.create().GetTerms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->
                    print(error)
                }
            )
    }

    @SuppressLint("CheckResult")
    fun GetContactData(livedata: MutableLiveData<Terms>?) {

        APIServices.create().GetContactData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->
                    print(error)
                }
            )
    }
    @SuppressLint("CheckResult")
    fun GetPartnersData(livedata: MutableLiveData<List<PartnersModel>>?) {

        APIServices.create().GetPartnersData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->
                    print(error)
                }
            )
    }
    @SuppressLint("CheckResult")

    fun PrintReport(oopo:String,auth:String,livedata: MutableLiveData<Buypackge>?) {

        APIServices.create().PrintReport(oopo,auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->
                    print(error)
                }
            )
    }

    @SuppressLint("CheckResult")

    fun GetMyImages(auth:String,livedata: MutableLiveData<List<SliderElement>>?) {

        APIServices.create().SliderData(auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->
                    print(error)
                }
            )
    }
}

