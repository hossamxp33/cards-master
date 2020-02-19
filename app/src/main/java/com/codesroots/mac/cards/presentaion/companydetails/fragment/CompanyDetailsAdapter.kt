package com.codesroots.mac.cards.presentaion.reportsFragment.adapters


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.CompanyDetailsItemBinding
import com.codesroots.mac.cards.databinding.MainAdapterBinding
import com.codesroots.mac.cards.databinding.ReportItemBinding
import com.codesroots.mac.cards.models.CompanyDatum
import com.codesroots.mac.cards.models.MyLocationUseCase
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
class CompanyDetailsAdapter ( var viewModel: MainViewModel,var context :Context?,var data:List<CompanyDatum>) : RecyclerView.Adapter<CustomViewHolders>() {
    override fun getItemCount(): Int {

        return  data.size

    }

    override fun onBindViewHolder(p0: CustomViewHolders, p1: Int) {
        p0.bind(viewModel,context,data.get(p1),viewModel)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolders {
//        val layoutInflater  = LayoutInflater.from(p0.context);
//        val cellforrow = layoutInflater.inflate(R.layout.main_adapter,p0,false);

//        val layoutParams = cellforrow.getLayoutParams()
//        layoutParams.height = (p0.getHeight() /  2).toInt()
//        layoutParams.width = (p0.getWidth() /  2.5).toInt()
//        cellforrow.setLayoutParams(layoutParams)
        val  binding: CompanyDetailsItemBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),R.layout.company_details_item,p0,false)

        return  CustomViewHolders(binding)
    }


}
class CustomViewHolders (
    private val binding:CompanyDetailsItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel:MainViewModel,context: Context?,data:CompanyDatum,viewModels: MainViewModel) {

        binding.listener = ClickHandler()
        binding.viewmodel = viewModels
        binding.data = data
        binding.context = context as MainActivity?
    }

}