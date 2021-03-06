package uz.drop.sortedlistexample

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(var name: String = "", var age: Int = 0) : Parcelable
