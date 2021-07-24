package com.example.onem2m_in_ae.service

import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.ResponseCon
import retrofit2.http.*

interface INAEDataService {
    @Headers(
        "Accept: application/json",
        "X-M2M-RI: 123aaghjhk45",
        "X-M2M-Origin: S",
        "Content-Type: application/json;ty=2",
    )
    @POST("/Mobius")
    suspend fun createAE(
        @Body param: RequestAE
    ): Unit

    @GET("/Mobius/junhyung_4")
    suspend fun getAEInfo(): ResponseAE

    @GET("/Mobius/IYAHN_DEMO/co2/la")
    suspend fun getContentInstanceLastResource(): ResponseCon

    @GET("/Mobius/IYAHN_DEMO/co2?fu=1&rcn=1")
    suspend fun getChildResourceContentInstanceInfo(): ResponseCon

    @GET("/Mobius/IYAHN_DEMO/co2?fu=2&rcn=4")
    suspend fun getDetailedChildResourceContentInstanceInfo(): ResponseCon

    @GET("/Mobius/IYAHN_DEMO/co2?fu=2&rcn=1")
    suspend fun getOwnResourceContentInstanceInfo(): ResponseCon

    @GET("/Mobius/IYAHN_DEMO/co2")
    suspend fun getContentInstanceResourceInfo(): ResponseCon
}