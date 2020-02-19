package com.codesroots.mac.cards.presentaion.reportsFragment;

import android.annotation.TargetApi
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.models.ReportDaily
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.reportsFragment.adapters.report_adapters
import com.codesroots.mac.cards.presentaion.snack
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.lastvalue
import kotlinx.android.synthetic.main.main_fragment.value
import kotlinx.android.synthetic.main.main_fragment.view.recyler
import kotlinx.android.synthetic.main.report_fragment.*
import kotlinx.android.synthetic.main.report_fragment.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@TargetApi(Build.VERSION_CODES.N)
class ReportsFragment  : Fragment() {

    lateinit var MainAdapter: report_adapters
    lateinit var viewModel: MainViewModel
    lateinit var reservationdate: TextView
    var year: Int = 0
    var month:Int = 0
    var day:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.report_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.GetMyDeialyReport(PreferenceHelper.getToken());
        viewModel.ReportDailyResponseLD?.observe(this , Observer {
            if (it.first().err != null){
                it.first().err!!.snack(( context as MainActivity).window.decorView.rootView)

            } else {
                val invitedPeople: List<ReportDaily> = it.filter { it.opid > 0 }

                MainAdapter = report_adapters(viewModel, context, invitedPeople)
                lastvalue.text = it.first().amount
                value.text = it.get(it.size - 2).amount
                println(it.get(it.size - 1).amount)
                commision.text = it.last().amount

                view.recyler.layoutManager = LinearLayoutManager(context);
                view.recyler.adapter = MainAdapter;
            }
        })



        val cal = Calendar.getInstance()

        view.to.setOnClickListener { v ->
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                Locale.setDefault(Locale.ENGLISH);

                v.to.text = SimpleDateFormat("yyyy-MM-dd").format(cal.time)
                viewModel.GetMyDatesReport(PreferenceHelper.getToken(),from.text.toString(),to.text.toString())
                viewModel.ReportDailyResponseLD?.observe(this , Observer {
                    lastvalue.text = it.first().amount
                    value.text = it.get(it.size - 2).amount
                    println(it.get(it.size - 2).amount)
                    commision.text = it.last().amount

                    MainAdapter.notifyDataSetChanged()

                })

            }, year, month, day)

            dpd.show()
        }
        view.from.setOnClickListener { v ->
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                Locale.setDefault(Locale.ENGLISH);

                v.from.text = SimpleDateFormat("yyyy-MM-dd").format(cal.time)



            }, year, month, day)

            dpd.show()
        }

        return view;


    }

}
