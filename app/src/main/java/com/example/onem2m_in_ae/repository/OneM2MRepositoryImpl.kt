package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.data.LocalDataSource
import com.example.onem2m_in_ae.data.RemoteDataSource
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.request.*
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.APP_ID

class OneM2MRepositoryImpl(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) : OneM2MRepository {

    companion object {
        const val resourceName = "demo"
    }

    override suspend fun createAE() {
        val requestAE = RequestAE(
            RequestM2mAE(
                resourceName,
                "0.2.481.2.0001.001.000111",
                arrayListOf("key1", "key2"),
                true
            )
        )
        return remoteDataSource.createAE(requestAE)
    }

    override suspend fun getAEInfo(): ResponseAE {
        return remoteDataSource.getAEInfo()
    }

    override suspend fun getContentInstanceInfo(resourceName: String): ResponseCin {
        return remoteDataSource.getContentInstanceInfo(resourceName)
    }

    override suspend fun getContainerInstanceInfoList(): List<ContainerInstance> {
        return localDataSource.getContainerInstanceInfoList()
    }

    override suspend fun getChildResourceInfo(): ResponseCntUril {
        return remoteDataSource.getChildResourceInfo()
    }

    override suspend fun getContainerInfo(): ResponseCnt {
        return remoteDataSource.getContainerInfo()
    }

    override suspend fun registerContainerInstance(
        containerInstance: ContainerInstance
    ) {
        return localDataSource.registerContainerInstance(containerInstance)
    }

    override suspend fun createSubscription(resourceName: String) {
        //cr -> x-m2m-origin 헤더값
        val subName = APP_ID
        val requestSub = RequestSub(
            RequestM2MSub(
                "sub",
                RequestEncNet(
                    arrayListOf(3, 4)
                ),
                arrayListOf("mqtt://192.168.10.62/${subName}_${resourceName}"),
                1,
                2,
                subName,
                100
            )
        )
        return remoteDataSource.createSubscription(requestSub, resourceName)
    }

    override suspend fun deviceControl(contentInstance: Map<String, String>) {
        val content = RequestCin(
            RequestM2MCin(contentInstance.getValue("content")))
        val resourceName = contentInstance.getValue("resourceName")

        return remoteDataSource.deviceControl(content, resourceName)
    }

    override suspend fun deleteContainer(resourceName: String) {
        return localDataSource.deleteContainer(resourceName)
    }
}