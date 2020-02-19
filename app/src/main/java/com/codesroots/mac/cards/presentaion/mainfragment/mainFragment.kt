package com.codesroots.mac.cards.presentaion.mainfragment

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.MainFragmentBinding
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.MainAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.SliderAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import java.util.*

class mainFragment  : Fragment(){

    lateinit var MainAdapter: MainAdapter
    lateinit var viewModel: MainViewModel
    private var currentPage = 0
    private var NUM_PAGES = 0
    var pager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:MainFragmentBinding =
            DataBindingUtil.inflate(inflater,R.layout.main_fragment, container,false)
        val typeface = Typeface.createFromAsset(getContext()!!.assets, "fonts/DroidKufi_Regular.ttf")
        pager = view.pager
        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getcompanyData()
        viewModel.getMyBalance()
        viewModel.GetMyImages(PreferenceHelper.getToken())
        viewModel.CompanyResponseLD?.observe(this , Observer {
            MainAdapter = MainAdapter( viewModel,context,it)
            view.recyler.layoutManager = GridLayoutManager(context,2)
            view.recyler.adapter = MainAdapter;
            view.textView11.typeface = typeface
            view.textView5.typeface = typeface
            view.lastvalue.typeface = typeface

            view.value.typeface = typeface

        })
        viewModel.MyBalanceResponseLD?.observe(this , Observer {

            view.myBalance = it
        })
        viewModel.SliderDataResponseLD?.observe(this , Observer {

            view.pager.adapter = it?.let { it1 -> SliderAdapter(activity!!, it1) }
            indicator.setViewPager(view.pager)

            it?.size?.let { it1 -> init(it1) }
        })
        return view.root;
    }
    private fun init(size: Int) {
        val density = getResources().getDisplayMetrics().density
        indicator.setRadius(4 * density)
        NUM_PAGES = size
        val handler = Handler()
        val Update:Runnable =Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            pager?.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 10000)
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }
}