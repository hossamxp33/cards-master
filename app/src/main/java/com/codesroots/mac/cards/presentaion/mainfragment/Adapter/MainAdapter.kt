package com.codesroots.mac.cards.presentaion.mainfragment.Adapter

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.MainAdapterBinding
import com.codesroots.mac.cards.models.CompanyData
import com.codesroots.mac.cards.models.CompanyDatum
import com.codesroots.mac.cards.models.MyLocationUseCase
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
class MainAdapter (var viewModel: MainViewModel,var context :Context?,var data:List<CompanyDatum>) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {

        return  data.size
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel,context,data.get(p1))

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val  binding: MainAdapterBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),R.layout.main_adapter,p0,false)
        val typeface = Typeface.createFromAsset(context!!.assets, "fonts/DroidKufi_Regular.ttf")
       // binding.textView7.typeface = typeface
        return  CustomViewHolder(binding)
    }


}
class CustomViewHolder (
    private val binding:MainAdapterBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel:MainViewModel,context: Context?,data:CompanyDatum) {

binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
    }

}
