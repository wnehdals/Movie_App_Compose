package com.jdm.data.mapper

import com.jdm.data.model.ModelWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.json.JSONObject
import retrofit2.Response

abstract class BaseMapper<M, E> {
    fun mapFromApi(remoteRep: Response<M>): ModelWrapper<E> {
        return if (remoteRep.isSuccessful) {
            getSuccess(remoteRep.body())
        } else {
            val json = JSONObject(remoteRep.errorBody().toString())
            try {
                val msg = json.getJSONObject("Error")
                getFailure("$msg")
            } catch (e: Exception) {
                getFailure("Unknown")
            }

        }
    }
    abstract fun getSuccess(model: M?): ModelWrapper.Success<E>

    abstract fun getFailure(msg: String): ModelWrapper.Fail<E>

}
abstract class SafeApiCall<M, E> {
    fun mapFromApi(remoteRep: Response<M>): Flow<ModelWrapper<E>> {
        return if (remoteRep.isSuccessful) {
            flowOf(getSuccess(remoteRep.body()))
        } else {
            val json = JSONObject(remoteRep.errorBody().toString())
            try {
                val msg = json.getJSONObject("Error")
                flowOf(getFailure("$msg"))
            } catch (e: Exception) {
                flowOf(getFailure("Unknown"))
            }

        }
    }
    abstract fun getSuccess(model: M?): ModelWrapper.Success<E>

    abstract fun getFailure(msg: String): ModelWrapper.Fail<E>
}