package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.liveData
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon
import com.example.onem2m_in_ae.repository.INAERepository
import com.example.onem2m_in_ae.view.base.BaseViewModel
import retrofit2.HttpException
import java.lang.Exception

class AirPurifierViewModel(private val inaeRepository: INAERepository) : BaseViewModel() {
    fun createSubscription(resourceName: String) = liveData {
        handle { inaeRepository.createSubscription(resourceName) }?.let {
            emit(it)
        }
    }

    fun getContentInstanceInfo(RESOURCE_NAME: String) = liveData<ResponseCnt>
    {
        val getContentInstanceInfo = handle { inaeRepository.getContentInstanceInfo(RESOURCE_NAME) }?.let {
            emit(it)
        }
    }

    val getContainerInfo = liveData<ResponseCon> {
        handle { inaeRepository.getContainerInfo() }?.let {
            emit(it)
        }
    }

    fun deviceControl(content: String, resourceName: String) = liveData {
        handle { inaeRepository.deviceControl(content, resourceName) }?.let {
            emit(it)
        }
    }

    fun deleteAirPurifierContainer(resourceName: String) = liveData {
        handle { inaeRepository.deleteContainer(resourceName) }?.let {
            emit(it)
        }
    }

    fun deleteDataBaseContainer(resourceName: String) = liveData {
        handle { inaeRepository.deleteDatabaseContainer(resourceName) }.let {
            emit(it)
        }
    }

    override fun onError(e: Exception) {
        super.onError(e)
        when (e) {
            is HttpException -> {
                when (e.code()) {
                    400 -> println("400: 잘못된 요청입니다.")
                    403 -> println("403: 접근 허용 거부입니다.")
                    404 -> println("404: 해당 url은 존재하지 않습니다.")
                    409 -> println("409: 이미 생성된 리소스가 있습니다.")
                    500 -> println("500: 서버 에러입니다.")
                }
            }
            else -> e.printStackTrace()
        }
    }

}