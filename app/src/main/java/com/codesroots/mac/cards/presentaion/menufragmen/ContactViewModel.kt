package com.codesroots.mac.cards.presentaion.menufragmen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codesroots.mac.cards.models.PartnersModel
import com.codesroots.mac.cards.models.Terms
import com.codesroots.mac.firstkotlon.DataLayer.Repo.DataRepo


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ContactViewModel() : ViewModel() {

    var contactMutableLiveData = MutableLiveData<Terms>()
    var PartnersMutableLiveData = MutableLiveData<List<PartnersModel>>()

    var error = MutableLiveData<Throwable>()
    var DateRepoCompnay: DataRepo = DataRepo()

    private val mCompositeDisposable = CompositeDisposable()
//
    init {
    contactMutableLiveData = MutableLiveData()
    PartnersMutableLiveData = MutableLiveData()

}


     fun getTermsData() {
        DateRepoCompnay.GetTermsData(contactMutableLiveData)
    }

    fun getContactData() {
        DateRepoCompnay.GetContactData(contactMutableLiveData)
    }
    fun getPartnersData() {
        DateRepoCompnay.GetPartnersData(PartnersMutableLiveData)
    }
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}
