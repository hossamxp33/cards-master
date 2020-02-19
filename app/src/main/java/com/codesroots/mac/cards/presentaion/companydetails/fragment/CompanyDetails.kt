package com.codesroots.mac.cards.presentaion.companydetails.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.presentaion.Printer.AidlUtil
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.reportsFragment.adapters.CompanyDetailsAdapter
import kotlinx.android.synthetic.main.company_details.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.runOnUiThread
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class CompanyDetails  : Fragment() {

    lateinit var MainAdapter: CompanyDetailsAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.company_details, container, false)
        viewModel = ViewModelProviders.of(this).get(
            MainViewModel::
            class.java
        )
        if (arguments != null) {
            AidlUtil.getInstance().initPrinter()
            val packageId = arguments?.getString("packageId")
            viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewModel.getPackageDetails(packageId!!)
            viewModel.CompanyResponseLD?.observe(this, Observer {


                MainAdapter = CompanyDetailsAdapter(viewModel, context, it)
                view.recyler.layoutManager = LinearLayoutManager(context)
                view.recyler.adapter = MainAdapter;

            })

        }
        return  view
    }

}