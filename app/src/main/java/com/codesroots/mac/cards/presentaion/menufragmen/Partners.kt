package com.codesroots.mac.cards.presentaion.menufragmen

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.MainFragmentBinding
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.PartnerAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.partner_layout.view.*

class Partners  : Fragment(){

    lateinit var MainAdapter: PartnerAdapter
    lateinit var viewModel: ContactViewModel
    private var currentPage = 0
    private var NUM_PAGES = 0
    var pager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.partner_layout, container, false)

        val typeface = Typeface.createFromAsset(getContext()!!.assets, "fonts/DroidKufi_Regular.ttf")
        viewModel =   ViewModelProviders.of(this).get(ContactViewModel::class.java)
        viewModel.getPartnersData()

        viewModel.PartnersMutableLiveData?.observe(this , Observer {
             MainAdapter = PartnerAdapter( viewModel,context,it)
            view.recyler.layoutManager = LinearLayoutManager(context)
            view.recyler.adapter = MainAdapter;
        })
viewModel.PartnersMutableLiveData.observe(this,Observer {


})

        return view;


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

