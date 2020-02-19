package com.codesroots.mac.cards.models


import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.codesroots.mac.firstkotlon.DataLayer.Repo.DataRepo

data class MyLocationUseCase (val videos: Video? = null) : BaseObservable()

{
    var clientsRepository: DataRepo = DataRepo()

    var id :Long? = null
    var ids :Int? = null
    var userId: String = ""

    val raring :Double? = null

    init {

        id   =  videos?.id
    }

    var is8Digit: Boolean = false

    @Bindable
    fun getPasswordTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                userId = s.toString()
                is8Digit = userId.length >=8
                notifyChange()
            }

            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        }
    }
}

