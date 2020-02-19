package com.codesroots.mac.cards.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "Buypackge")
@Parcelize
data class Buypackge (
    val opno: Int? = null,
    val salor: String? = null,
    val time: String? = null,
    val device: String? = null,
    val name: String? = null,
    val src: String? = null,
    val price: String? = null,
    val notes: String? = null,
    val count: String? = null,
    val err: String? = null,
val serial:String? = null

):Parcelable {

        @PrimaryKey(autoGenerate = true)
        var id : Int = 0
@Ignore
var pencode:List<Pencode>? = null

}

data class LogData(
    val auth: String,
    val err: Int

)
data class MyBalance (
    val account: String? = null,
    val commession: String? = null
)
data class ReportDaily (
    val opid: Long,
    val opno: String,
    val opdate: String,
    val strcase: String,
    val amount: String,
    val err: String? = null,
    val cardname: String? = null,
    val logo:String? = null
    )

@Entity(tableName = "pencode")
@Parcelize
data class Pencode (
    val pencode: String? = null,
    val expdate: String? = null,
    val serial: String? = null,
    var buypackageid: Int

    ):Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

data class Terms (
    val headline: String,
    val details: String,
    val mobile: String,
    val email: String,
    val fb: String
)