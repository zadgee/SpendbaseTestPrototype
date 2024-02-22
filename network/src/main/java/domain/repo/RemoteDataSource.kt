package domain.repo
import data.models.CardsListModel
import data.models.TransactionsListModel
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getCardPage(page:Int?=null, pageCount:Int?=null):Response<CardsListModel>
    suspend fun getTransactionsList(page:Int?=null, pageCount:Int?=null):Response<TransactionsListModel>
}