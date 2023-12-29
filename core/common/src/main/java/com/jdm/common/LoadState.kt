package com.jdm.common

sealed class LoadState {
    object Undefine : LoadState()
    object Loading : LoadState()
    object Success : LoadState()
    object Fail : LoadState()
}
