package domain.repository
import domain.models.CardMockModel
import domain.models.TransactionMockModel

interface FinancialRepository {
    suspend fun getCardPage(page:Int, pageCount:Int):List<CardMockModel>
    suspend fun getTransactionsPage(page:Int, pageCount: Int):List<TransactionMockModel>
}