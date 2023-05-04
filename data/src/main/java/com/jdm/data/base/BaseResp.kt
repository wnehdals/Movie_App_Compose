package com.jdm.data.base

import com.google.gson.annotations.SerializedName

open class BaseResp {
    @SerializedName("Response")
    val response: String = "False"

    @SerializedName("Error")
    val error: String? = null
}