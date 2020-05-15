package com.example.challenge2.data

import com.google.gson.annotations.SerializedName

data class IndoItem(

	@field:SerializedName("meninggal")
	val meninggal: String? = null,

	@field:SerializedName("positif")
	val positif: String? = null,

	@field:SerializedName("sembuh")
	val sembuh: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)
