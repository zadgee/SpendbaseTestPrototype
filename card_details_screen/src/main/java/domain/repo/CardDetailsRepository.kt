package domain.repo
import androidx.paging.PagingData
import domain.models.TransactionsMockDTO
import kotlinx.coroutines.flow.Flow

interface CardDetailsRepository {
     fun getTransactionsList():Flow<PagingData<TransactionsMockDTO>>
}