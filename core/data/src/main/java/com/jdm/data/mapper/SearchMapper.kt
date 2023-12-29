package com.jdm.data.mapper

import com.jdm.model.Search
import com.jdm.data.model.ModelWrapper
import com.jdm.network.model.SearchResp

class SearchMapper : BaseMapper<SearchResp, Search>() {
    override fun getSuccess(model: SearchResp?): ModelWrapper.Success<Search> {
        return model?.let {
            ModelWrapper.Success(entity = it.toSearch())
        } ?: ModelWrapper.Success(entity = Search())
    }

    override fun getFailure(msg: String): ModelWrapper.Fail<Search> {
        return ModelWrapper.Fail(msg)
    }
}
