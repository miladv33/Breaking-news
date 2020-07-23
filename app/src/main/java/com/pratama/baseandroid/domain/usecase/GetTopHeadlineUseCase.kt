package com.pratama.baseandroid.domain.usecase

import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.coreandroid.usecase.UseCase
import com.pratama.baseandroid.data.repository.NewsRepository
import com.pratama.baseandroid.domain.entity.News
import javax.inject.Inject

class GetTopHeadlineUseCase @Inject constructor(private val repository: NewsRepository) :
    UseCase<List<News>, GetTopHeadlineUseCase.TopHeadlineParam>() {

    override suspend fun run(params: TopHeadlineParam): Either<Failure, List<News>> {
        return repository.getTopHeadlines(params.country, params.category)
    }

    data class TopHeadlineParam(
        val country: String,
        val category: String
    )
}