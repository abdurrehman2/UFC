package org.wit.ufc.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UfcModel(var title: String = "",
                    var id: Long=0 ,
var description: String = "",


  var name: String = "" ,
  var age: String = "" ,
  var weight: String = "" ,
   var record: String = "",
    var image: String = "",
                    var lat : Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable



@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable

