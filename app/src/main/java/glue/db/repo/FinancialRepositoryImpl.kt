package glue.db.repo

import domain.models.CardMockModel
import domain.models.TransactionMockModel
import domain.repository.FinancialRepository
import domain.usecases.GetCardsUseCase
import domain.usecases.GetTransactionsUseCase
import glue.db.mapper.toMockModel
import glue.db.mapper.toTransactionMockModel
import javax.inject.Inject

class FinancialRepositoryImpl @Inject constructor(
    private val getCardsUseCase: GetCardsUseCase,
    private val getTransactionsPageUseCase: GetTransactionsUseCase
): FinancialRepository {

    override suspend fun getCardPage(page: Int, pageCount: Int): List<CardMockModel> {
        return getCardsUseCase.retrieve(page, pageCount).map { it.toMockModel() }
    }

    override suspend fun getTransactionsPage(page: Int, pageCount: Int):List<TransactionMockModel>{
        return getTransactionsPageUseCase.retrieve(page, pageCount).map { it.toTransactionMockModel() }
    }


}