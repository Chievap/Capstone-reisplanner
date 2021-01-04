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
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Station

        if (uICCode != other.uICCode) return false
        if (stationType != other.stationType) return false
        if (EVACode != other.EVACode) return false
        if (code != other.code) return false
        if (!sporen.contentEquals(other.sporen)) return false
        if (!synoniemen.contentEquals(other.synoniemen)) return false
        if (faciliteiten != other.faciliteiten) return false
        if (vertrektijden != other.vertrektijden) return false
        if (reisassistentie != other.reisassistentie) return false
        if (namen != other.namen) return false
        if (land != other.land) return false
        if (lat != other.lat) return false
        if (lng != other.lng) return false
        if (radius != other.radius) return false
        if (naderenRadius != other.naderenRadius) return false
        if (ingangsDatum != other.ingangsDatum) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uICCode.hashCode()
        result = 31 * result + stationType.hashCode()
        result = 31 * result + EVACode.hashCode()
        result = 31 * result + code.hashCode()
        result = 31 * result + sporen.contentHashCode()
        result = 31 * result + synoniemen.contentHashCode()
        result = 31 * result + faciliteiten.hashCode()
        result = 31 * result + vertrektijden.hashCode()
        result = 31 * result + reisassistentie.hashCode()
        result = 31 * result + namen.hashCode()
        result = 31 * result + land.hashCode()
        result = 31 * result + lat.hashCode()
        result = 31 * result + lng.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + naderenRadius.hashCode()
        result = 31 * result + ingangsDatum.hashCode()
        return result
    }
}

data class Names(
    @SerializedName("lang") var lang: String,
    @SerializedName("middel") var middel: String,
    @SerializedName("kort") var kort: String,
)