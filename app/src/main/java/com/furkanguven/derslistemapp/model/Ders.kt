package com.furkanguven.derslistemapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Ders(
        @ColumnInfo(name = "isim")
        @SerializedName("isim")
        val dersIsim: String?,
        @ColumnInfo(name = "devre")
        @SerializedName("devre")
        val dersDevre: String?,
        @ColumnInfo(name = "Hafta 1")
        @SerializedName("Hafta 1")
        val Hafta1: String?,
        @ColumnInfo(name = "Hafta 2")
        @SerializedName("Hafta 2")
        val Hafta2: String?,
        @ColumnInfo(name = "Hafta 3")
        @SerializedName("Hafta 3")
        val Hafta3: String?,
        @ColumnInfo(name = "Hafta 4")
        @SerializedName("Hafta 4")
        val Hafta4: String?,
        @ColumnInfo(name = "Hafta 5")
        @SerializedName("Hafta 5")
        val Hafta5: String?,
        @ColumnInfo(name = "Hafta 6")
        @SerializedName("Hafta 6")
        val Hafta6: String?,
        @ColumnInfo(name = "Hafta 7")
        @SerializedName("Hafta 7")
        val Hafta7: String?,
        @ColumnInfo(name = "Hafta 8")
        @SerializedName("Hafta 8")
        val Hafta8: String?,
        @ColumnInfo(name = "Hafta 9")
        @SerializedName("Hafta 9")
        val Hafta9: String?,
        @ColumnInfo(name = "Hafta 10")
        @SerializedName("Hafta 10")
        val Hafta10: String?,
        @ColumnInfo(name = "Hafta 11")
        @SerializedName("Hafta 11")
        val Hafta11: String?,
        @ColumnInfo(name = "gorsel")
        @SerializedName("gorsel")
        val dersGorsel: String?
) {
        @PrimaryKey(autoGenerate = true)
        var uuid : Int =0
}