import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Stops (

		@SerializedName("uicCode") val uicCode : Int,
		@SerializedName("name") val name : String,
		@SerializedName("lng") val lng : Double,
		@SerializedName("lat") val lat : Double,
		@SerializedName("countryCode") val countryCode : String,
		@SerializedName("notes") val notes : List<String>,
		@SerializedName("routeIdx") val routeIdx : Int,
		@SerializedName("departurePrognosisType") val departurePrognosisType : String,
		@SerializedName("plannedDepartureDateTime") val plannedDepartureDateTime : String,
		@SerializedName("plannedDepartureTimeZoneOffset") val plannedDepartureTimeZoneOffset : Int,
		@SerializedName("actualDepartureTrack") val actualDepartureTrack : Int,
		@SerializedName("plannedDepartureTrack") val plannedDepartureTrack : Int,
		@SerializedName("cancelled") val cancelled : Boolean,
		@SerializedName("borderStop") val borderStop : Boolean,
		@SerializedName("passing") val passing : Boolean
)