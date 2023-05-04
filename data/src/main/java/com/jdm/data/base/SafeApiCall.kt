@file:OptIn(FlowPreview::class)

package com.jdm.data.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

suspend fun <DATA, DOMAIN> apiCall(
    call: suspend () -> DATA,
    mapper: (DATA) -> DOMAIN,
): Flow<Result<DOMAIN>> = call.asFlow()
    .map {
        Log.e("jdm_tag", mapper(it).toString())
        Result.success(mapper(it))
    }
    .catch { emit(Result.failure(it)) }
    .flowOn(Dispatchers.IO)
