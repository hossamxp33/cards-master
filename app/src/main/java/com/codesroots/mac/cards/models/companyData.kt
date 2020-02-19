package com.codesroots.mac.cards.models



typealias CompanyData = List<CompanyDatum>



data class CompanyDatum (
    val id: String? = null,
    val name: String? = null,
    val src: String? = null,
    val sprice: String? = null

)
