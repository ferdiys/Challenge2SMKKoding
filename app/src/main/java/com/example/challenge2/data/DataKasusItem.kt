package com.example.challenge2.data

import com.google.gson.annotations.SerializedName

data class DataKasusItem(

    @field:SerializedName("dailyTimeSeries")
	val dailyTimeSeries: DailyTimeSeries? = null,

    @field:SerializedName("image")
	val image: String? = null,

    @field:SerializedName("recovered")
	val recovered: Recovered? = null,

    @field:SerializedName("dailySummary")
	val dailySummary: String? = null,

    @field:SerializedName("lastUpdate")
	val lastUpdate: String? = null,

    @field:SerializedName("countryDetail")
	val countryDetail: CountryDetail? = null,

    @field:SerializedName("source")
	val source: String? = null,

    @field:SerializedName("countries")
	val countries: String? = null,

    @field:SerializedName("confirmed")
	val confirmed: Confirmed? = null,

    @field:SerializedName("deaths")
	val deaths: Deaths? = null
)

data class Recovered(

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class Deaths(

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class Confirmed(

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class DailyTimeSeries(

	@field:SerializedName("pattern")
	val pattern: String? = null,

	@field:SerializedName("example")
	val example: String? = null
)

data class CountryDetail(

	@field:SerializedName("pattern")
	val pattern: String? = null,

	@field:SerializedName("example")
	val example: String? = null
)
