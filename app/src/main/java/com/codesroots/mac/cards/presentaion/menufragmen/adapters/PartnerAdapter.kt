package com.codesroots.mac.cards.presentaion.mainfragment.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.MainAdapterBinding
import com.codesroots.mac.cards.databinding.PartnerItemBinding
import com.codesroots.mac.cards.models.CompanyData
import com.codesroots.mac.cards.models.CompanyDatum
import com.codesroots.mac.cards.models.MyLocationUseCase
import com.codesroots.mac.cards.models.PartnersModel
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.menufragmen.ContactViewModel

class PartnerAdapter (var viewModel: ContactViewModel, var context :Context?, var data:List<PartnersModel>) : RecyclerView.Adapter<CustomViewHolders>() {
    override fun getItemCount(): Int {

        return  data.size
    }

    override fun onBindViewHolder(p0: CustomViewHolders, p1: Int) {
        p0.bind(viewModel,context,data.get(p1))

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolders {

        val  binding: PartnerItemBinding  = DataBindingUtil.inflate (LayoutInflater.from(p0.context),R.layout.partner_item,p0,false)
        val typeface = Typeface.createFromAsset(context!!.assets, "fonts/DroidKufi_Regular.ttf")
        binding.textView7.typeface = typeface
        binding.appCompatImageView.setOnClickListener { v -> openNewTabWindow(data.get(p1).linkpath, context!!) }
        return  CustomViewHolders(binding)
    }
    fun openNewTabWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }

}
class CustomViewHolders (
    private val binding:PartnerItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel:ContactViewModel,context: Context?,data:PartnersModel) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
    }

}
