package com.example.onem2m_in_ae.model

import com.google.gson.annotations.SerializedName

data class RequestCon (
    @SerializedName("m2m:cin")
    val m2mAdn: RequestM2MCon
)