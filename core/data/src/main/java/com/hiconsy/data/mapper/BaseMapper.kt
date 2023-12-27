package com.hiconsy.data.mapper

import com.hiconsy.data.model.ModelWrapper
import org.json.JSONObject
import retrofit2.Response

abstract class BaseMapper<M, E> {
    fun mapFromApi(remoteRep: Response<M>): ModelWrapper<E> {
        return if (remoteRep.isSuccessful) {
            getSuccess(remoteRep.body())
        } else {
            val json = JSONObject(remoteRep.errorBody().toString())
            getFailure("${json.getJSONObject("error")} ${json.getJSONObject("message")}")
        }
    }
    abstract fun getSuccess(model: M?): ModelWrapper.Success<E>

    abstract fun getFailure(msg: String): ModelWrapper.Fail<E>

}