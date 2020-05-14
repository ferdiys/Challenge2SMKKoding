package com.example.challenge2.data

import com.google.gson.annotations.SerializedName

data class ProvinsiItem(

	@field:SerializedName("attributes")
	val data: Item? = null
)

data class Item(

	@field:SerializedName("FID")
	val fID: Int? = null,

	@field:SerializedName("Kode_Provi")
	val kodeProvi: Int? = null,

	@field:SerializedName("Kasus_Meni")
	val kasusMeni: Int? = null,

	@field:SerializedName("Kasus_Posi")
	val kasusPosi: Int? = null,

	@field:SerializedName("Provinsi")
	val provinsi: String? = null,

	@field:SerializedName("Kasus_Semb")
	val kasusSemb: Int? = null
)
