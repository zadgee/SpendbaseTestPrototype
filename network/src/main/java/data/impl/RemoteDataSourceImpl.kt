package data.impl

import data.models.CardsListModel
import data.models.TransactionsListModel
import data.service.CardsService
import data.service.TransactionsService
import domain.repo.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val cardsApi: CardsService,
    private val transactionsApi:TransactionsService,
): RemoteDataSource {


    override suspend fun getCardPage(page: Int?, pageCount: Int?): Response<CardsListModel> {
        return cardsApi.getCardPage(page, pageCount)
    }

    override suspend fun getTransactionsList(
        page: Int?,
        pageCount: Int?
    ): Response<TransactionsListModel> {
        return transactionsApi.getTransactionsList(page, pageCount)
    }
}