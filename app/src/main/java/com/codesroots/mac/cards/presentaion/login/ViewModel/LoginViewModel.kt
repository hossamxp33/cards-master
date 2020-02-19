package com.codesroots.hossam.mandoobapp.presentation.login.ViewModel



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.codesroots.hossam.mandoobapp.presentation.login.repository.LoginRepository
import com.codesroots.mac.cards.models.LogData
import com.codesroots.mac.firstkotlon.DataLayer.Repo.DataRepo

import java.util.ArrayList

class LoginViewModel : ViewModel() {
     var loginRepository: LoginRepository = LoginRepository()


    var coderesponse = MutableLiveData<Int>()
    var DateRepoCompnay: DataRepo = DataRepo()

    private val error = MutableLiveData<String>()

    var loginResponseLD = MutableLiveData<LogData>()

    init {

        loginResponseLD = MutableLiveData()

    }

    fun Login(username:String,password:String) {
        DateRepoCompnay.Login(username,password,loginResponseLD)
  }

}