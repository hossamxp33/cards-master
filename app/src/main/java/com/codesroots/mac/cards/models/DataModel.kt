package com.codesroots.mac.cards.models


data class MyLocation (
    val videos: List<Video>? = null
)

data class Video (
    val id: Long? = 0,
    val name: String? = null,
    val link: String? = null,
    val imageURL: String? = null,
    val numberOfViews: Long? = null,
    val channel: Channel? = null


)

data class Channel (
    val name: String? = null,
    val profileImageURL: String? = null,
    val numberOfSubscribers: Long? = null
)


data class  SliderData (
    val data: List<SliderElement>? = null
)
data class SliderElement (
    val no: Long,
    val headline: String,
    val image: String
)

data class PartnersModel (
    val no: Long,
    val headline: String,
    val linkpath: String
)

