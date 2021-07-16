package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.service.INAEDataService

class INAERemoteDataSource(
    val inAEDataService: INAEDataService
) : INAEDataSource {
    override suspend fun createAEINInfoList(param: RequestAE): Unit {
        return inAEDataService.createAEInfoList(param)
    }

    override suspend fun getAE(): ResponseAE {
        return inAEDataService.getAE()
    }
}