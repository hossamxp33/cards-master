package com.codesroots.mac.cards.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class CardDB(
    val opno: String? = null,
    val salor: String? = null,
    val time: String? = null,
    val device: String? = null,
    var name: String? = null,
    val src: String? = null,
    val price: String? = null,
    val notes: String? = null,
    val count: String? = null,
    val err: String? = null,
    val serial:String? = null
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
