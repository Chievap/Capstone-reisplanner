package com.example.capstonereisplanner.model

import com.google.gson.annotations.SerializedName

data class Station (
    @SerializedName("UICCode") var uICCode: Float,
    @SerializedName("stationType") var stationType: String,
    @SerializedName("EVACode") var EVACode: Float,
    @SerializedName("code") var code: String,
    @SerializedName("sporen") var sporen: Array<Float>,
    @SerializedName("synoniemen") var synoniemen: Array<String>,
    @SerializedName("heeftFaciliteiten") var faciliteiten: Boolean,
    @SerializedName("heeftVertrektijden") var vertrektijden: Boolean,
    @SerializedName("heeftReisassistentie") var reisassistentie: Boolean,
    @SerializedName("namen") var namen: Names,
    @SerializedName("land") var land: String,
    @SerializedName("lat") var lat: Long,
    @SerializedName("lng") var lng: Long,
    @SerializedName("radius") var radius: Float,
    @SerializedName("naderenRadius") var naderenRadius: Float,
    @SerializedName("ingangsDatum") var ingangsDatum: String,
){
    fun getFullName() = namen.lang
    fun getMiddleName() = namen.middel
    fun getShortName() = namen.kort
}

data class Names(
    @SerializedName("lang") var lang: String,
    @SerializedName("middel") var middel: String,
    @SerializedName("kort") var kort: String,
)