package com.codesroots.mac.cards.presentaion.menufragmen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.DataLayer.usecases.openWhatsApp
import com.codesroots.mac.cards.R
import kotlinx.android.synthetic.main.contact_fragment.*
import kotlinx.android.synthetic.main.contact_fragment.view.*
import org.jetbrains.anko.support.v4.email
import org.jetbrains.anko.support.v4.makeCall


class ContactFragment : Fragment() {
    lateinit var contactViewModel: ContactViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.contact_fragment, container, false)

        view.whats.setOnClickListener { v -> openWhatsApp(phone2.text.toString(), context!!) }
        view.phone2.setOnClickListener { v -> makeCall(phone2.text.toString()) }
        return view
    }
    fun openNewTabWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactViewModel =
            ViewModelProviders.of(this).get(ContactViewModel::class.java)
//
        contactViewModel.getContactData()
        contactViewModel.contactMutableLiveData?.observe(this , Observer {
            phone2.text = it.mobile
            mail.text = it.email
           insta.setOnClickListener { v -> openNewTabWindow(it.fb, context!!) }

            phone2.setMovementMethod(ScrollingMovementMethod());
        })

//        contactViewModel.error.observe(
//            this,
//            { throwable ->
//                Toast.makeText(
//                    activity,
//                    getText(R.string.erroroccure),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        )
    }


}
