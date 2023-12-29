package com.jdm.data.model

sealed class ModelWrapper<T> {
    data class Success<T>(val entity: T) : ModelWrapper<T>()
    data class Fail<T>(val error: String) : ModelWrapper<T>()
}
