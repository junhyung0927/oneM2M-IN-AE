package com.example.onem2m_in_ae.model.request

import com.google.gson.annotations.SerializedName

data class RequestM2MSub(
    val rn: String,
    @SerializedName("enc")
    val enc: RequestEncNet,
    val nu: ArrayList<String>
)