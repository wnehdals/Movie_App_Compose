package com.jdm.domain.usecase

import com.jdm.domain.model.Search
import com.jdm.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(keyword: String, page: Int): Flow<Result<Search>> {
        return movieRepository.getSearchResp(keyword, page)
    }
}