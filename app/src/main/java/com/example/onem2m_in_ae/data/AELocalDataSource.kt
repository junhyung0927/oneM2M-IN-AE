package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.room.INAEDao

class AELocalDataSource(
    private val inAEDao: INAEDao
) : LocalDataSource
{
    override fun createContentInstance(containerInstance: List<ContainerInstance>)
    {
        inAEDao.createContainerInstance(containerInstance)
    }

    override suspend fun insertContentInstanceInfoList(containerInstance: List<ContainerInstance>)
    {
        return inAEDao.insertContainerInstanceList(*containerInstance.toTypedArray())
    }

    override suspend fun getContainerInstanceDataBase(): List<ContainerInstance>
    {
        return inAEDao.getContainerInstanceInfoList()
    }

    override suspend fun registerContainerInstance(containerInstance: List<ContainerInstance>) {
        return inAEDao.registerContainerInstance(containerInstance)
//        return inAEDao.deleteAll()
    }
}