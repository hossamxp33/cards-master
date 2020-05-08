package com.codesroots.mac.cards.presentaion

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isGone
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.db.CardDao
import com.codesroots.mac.cards.db.CardDatabase
import com.codesroots.mac.cards.models.Buypackge
import com.codesroots.mac.cards.presentaion.Printer.AidlUtil

import com.codesroots.mac.cards.presentaion.companydetails.fragment.CompanyDetails
import com.codesroots.mac.cards.presentaion.mainfragment.mainFragment
import com.codesroots.mac.cards.presentaion.portifliofragment.PortiflioFragment

import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.menufragmen.MenuFragment
import com.codesroots.mac.cards.presentaion.payment.Payment
import com.codesroots.mac.cards.presentaion.reportsFragment.ReportsFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert_add_reserve.view.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.runOnUiThread

import java.io.IOException
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var homeFragment: mainFragment
    lateinit var reportsFragment: ReportsFragment
    lateinit var moreFragment: MenuFragment
    lateinit var wallet: PortiflioFragment

    lateinit var navigationView: NavigationView
    override fun onResume() {
        super.onResume()
        println("onressomes")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ///////// tool bar and drawerToggle
        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar?.title = ""
        val drawerToggle: ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(this, drawerLayout, toolBar, (R.string.open), (R.string.close)) {

            }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        homeFragment = mainFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()



        AidlUtil.getInstance().connectPrinterService(this)
        PreferenceHelper(this)

        // Create messages
        val item1 = AHBottomNavigationItem(
            R.string.tab_1,
            R.drawable.house_outline, R.color.signinpurple
        )
        val item2 = AHBottomNavigationItem(
            R.string.tab_2,
            R.drawable.analytics, R.color.signinpurple
        )
        val item3 = AHBottomNavigationItem(
            R.string.tab_3,
            R.drawable.more, R.color.signinpurple
        )
        val item4 = AHBottomNavigationItem(
            R.string.tab_4,
            R.drawable.work, R.color.signinpurple
        )
        with(bottom_navigation) {
            addItems(listOf(item2, item1,item3,item4))
            inactiveColor = ContextCompat.getColor(context ,R.color.gray )
            accentColor  =  ContextCompat.getColor(context ,R.color.signinpurple )

            currentItem = 1

    setOnTabSelectedListener { position, wasSelected ->Unit
        Log.e( "tab positiion",position.toString())
        /*  getLastLocation()*/

        if (position==3) {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                .replace(com.codesroots.mac.cards.R.id.main_frame, PortiflioFragment()).addToBackStack(null).commit()
        }
        if (position==2) {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                .replace(com.codesroots.mac.cards.R.id.main_frame, MenuFragment()).addToBackStack(null).commit()
        }
        if (position==1) {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                .replace(R.id.main_frame, mainFragment()).addToBackStack(null).commit()
        }
        if (position == 0){
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                .replace(R.id.main_frame, ReportsFragment()).addToBackStack(null).commit()


        }
        true

    }
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                .replace(R.id.main_frame, mainFragment() , "Main").addToBackStack(null).commit()

        }

    }
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {
            R.id.home -> {
                homeFragment = mainFragment()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()


            }
            R.id.reports -> {
                reportsFragment = ReportsFragment()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, reportsFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.more -> {
                moreFragment = MenuFragment()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, moreFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.more -> {
                wallet = PortiflioFragment()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, wallet)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.isDrawerOpen(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }
}

class ClickHandler {

    var  mLastClickTime: Long = 0

    fun SwitchToPackages( context: Context,comid :String) {

        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = CompanyDetails()
        frag.arguments = bundle
        bundle.putString("packageId" , comid)
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(R.anim.ttb,0, 0,0)
            .replace(R.id.main_frame, frag).addToBackStack(null).commit()
    }
    fun SwitchToReports( context: Context,comid :String) {

        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = ReportsFragment()
        frag.arguments =bundle
        bundle.putString("packageId" , comid)
        ( context as MainActivity).supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
            .replace(R.id.main_frame, frag).addToBackStack(null).commit()
    }

    fun SwitchToPayment(context: Context,id:String,viewmodel:MainViewModel) {

        val dialogBuilder = AlertDialog.Builder(( context as MainActivity) )
        val inflater = ( context as MainActivity).getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.alert_add_reserve, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
    var  title =  TextView(context as MainActivity);
// You Can Customise your Title here
         title.setText("إضافة طلب");
title.setBackgroundColor(Color.DKGRAY);
title.setPadding(10, 10, 10, 10);
title.setGravity(Gravity.CENTER);
title.setTextSize(20f);

        dialogBuilder.setCustomTitle(title);
        alertDialog.show()
            dialogView.save.setOnClickListener { v: View? ->
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return@setOnClickListener
                }
                v!!.isGone = true
                mLastClickTime = SystemClock.elapsedRealtime();
                val auth = PreferenceHelper.getToken()
                viewmodel.BuyPackage(id,auth!!,dialogView.from.text.toString())

                if (viewmodel.BuyPackageResponseLD?.hasObservers() == false) {
                    viewmodel.BuyPackageResponseLD?.observe(context, Observer {


                            if (it.err != null) {
                                it.err!!.snack((context as MainActivity).window.decorView.rootView)
                                dialogView.err.text = it.err
                                dialogView.err.isGone = false
                            } else {
                                if (!it!!.pencode.isNullOrEmpty()) {

Glide.with(context as MainActivity)
    .asBitmap()
    .load("http://across-cities.com/"+it.src)
    .into(object : SimpleTarget<Bitmap>(100, 100) {
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            try {
                AidlUtil.getInstance().printRecipte(it,resource);

                val homeIntent = Intent(context, Payment::class.java)

                homeIntent.putExtra("myobj", it)
                (context as MainActivity).startActivity(homeIntent)

            }
            catch (e: IOException) {
                // handle exception
            }
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            // handle exception
        }
    })
                                    }
                                    }
                    })
                }
            }
            dialogView.saveToRoom.setOnClickListener { v: View? ->
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return@setOnClickListener
            }
            v!!.isGone = true
            mLastClickTime = SystemClock.elapsedRealtime();
            val auth = PreferenceHelper.getToken()
            viewmodel.BuyPackage(id,auth!!,dialogView.from.text.toString())

            if (viewmodel.BuyPackageResponseLD?.hasObservers() == false) {
                viewmodel.BuyPackageResponseLD?.observe(context, Observer {


                    if (it.err != null) {
                        it.err!!.snack((context as MainActivity).window.decorView.rootView)
                        dialogView.err.text = it.err
                        dialogView.err.isGone = false
                    } else {
                        if (!it!!.pencode.isNullOrEmpty()) {
        Thread {
            val db = Room.databaseBuilder(
                context,
                CardDatabase::class.java, "card-database"
            ).build()
            insertUserWithPet(it, db.getCardDao())
            "تم الحفظ بالمحفظة بنجاح".snack((context).window.decorView.rootView)
        }.start()

                   GlobalScope.launch {
                       val db = Room.databaseBuilder(
                          context,
                           CardDatabase::class.java, "card-database"
                       ).build()
                       db.getCardDao().GetAllData()
                  "تم الحفظ بالمحفظة بنجاح".snack((context).window.decorView.rootView)
                   }


                        }

                    }

                })
            }
        }
    }

    fun insertUserWithPet(user: Buypackge, cardDao: CardDao) {
        val pets = user.pencode!!
        for (i in pets.indices) {
            pets.get(i).buypackageid = user.opno!!
        }
        cardDao.insertPetList(pets)
        cardDao. insertPackege(user)
    }

    fun  deletedata(context: MainActivity,its:Buypackge) {
        GlobalScope.launch {
            let {
            val db = Room.databaseBuilder(context ,
                CardDatabase::class.java, "card-database"
            ).build()
            db.getCardDao().deleteCard(its.opno!!)

        }}

    }
    suspend fun getUserWithPets(id: Int, room: CardDao): Buypackge {
        val user = room.getUser(id)
        val pets = room.getPetList(id)
        user.pencode = pets
        return user
    }
    fun ShowReport(context: Context,id:String) {

        lateinit var viewModel: MainViewModel
        val auth = PreferenceHelper.getToken()
        viewModel =   ViewModelProviders.of(( context as MainActivity)).get(MainViewModel::class.java)
        viewModel.PrintReport(id,auth!!)
        if (viewModel.BuyPackageResponseLD?.hasObservers() == false) {

            viewModel.BuyPackageResponseLD?.observe(context, Observer {
                if (it.err != null) {
                    it.err!!.snack((context as MainActivity).window.decorView.rootView)


                } else {
                    if (!it!!.pencode.isNullOrEmpty()) {


                        Glide.with(context as MainActivity)
                            .asBitmap()
                            .load("http://across-cities.com/" + it.src)
                            .into(object : SimpleTarget<Bitmap>(100, 100) {
                                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                    try {

                                        val homeIntent = Intent(context, Payment::class.java)

                                        homeIntent.putExtra("myobj", it)

                                        (context as MainActivity).startActivity(homeIntent)

                                    } catch (e: IOException) {
                                        // handle exception
                                    }
                                }

                                override fun onLoadFailed(errorDrawable: Drawable?) {
                                    // handle exception
                                }
                            })
                    }
                }
            })
        }

    }

    fun PrintReport(context: Context,id:String) {

        lateinit var viewModel: MainViewModel

            val auth = PreferenceHelper.getToken()
            viewModel =   ViewModelProviders.of(( context as MainActivity)).get(MainViewModel::class.java)
            viewModel.PrintReport(id,auth!!)
        if (viewModel.BuyPackageResponseLD?.hasObservers() == false) {

            viewModel.BuyPackageResponseLD?.observe(context, Observer {
                if (it.err != null) {
                    it.err!!.snack((context as MainActivity).window.decorView.rootView)


                } else {
                    if (!it!!.pencode.isNullOrEmpty()) {


                        Glide.with(context as MainActivity)
                            .asBitmap()
                            .load("http://across-cities.com/" + it.src)
                            .into(object : SimpleTarget<Bitmap>(100, 100) {
                                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                    try {
                                        AidlUtil.getInstance().printRecipte(it, resource);


                                    } catch (e: IOException) {
                                        // handle exception
                                    }
                                }

                                override fun onLoadFailed(errorDrawable: Drawable?) {
                                    // handle exception
                                }
                            })
                    }
                }
            })
        }

        }

    fun PrintReportFromPortifolio(context: Context,its:Buypackge) {
        var card: Buypackge? = null

        GlobalScope.launch {
            context.let {
                val db = Room.databaseBuilder(
                    it,
                    CardDatabase::class.java, "card-database"
                ).build()
                var data = getUserWithPets(its.opno!!, db.getCardDao())
                card = data
                if (!card!!.pencode.isNullOrEmpty()) {
                    Thread {
                        context!!.runOnUiThread {
                            Glide.with(context as MainActivity)
                                .asBitmap()
                                .load("http://across-cities.com/" + card!!.src)
                                .into(object : SimpleTarget<Bitmap>(100, 100) {
                                    override fun onResourceReady(
                                        resource: Bitmap,
                                        transition: Transition<in Bitmap>?
                                    ) {
                                        try {
                                            AidlUtil.getInstance().printRecipte(card, resource);
                                            deletedata(context, card!!)


                                        } catch (e: IOException) {
                                            // handle exception
                                        }
                                    }

                                    override fun onLoadFailed(errorDrawable: Drawable?) {
                                        // handle exception
                                    }
                                })
                        }
                    }.start()
                }
            }
        }
    }
//thread {
//
//}.start()














}
