package glue.card_details.repo

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import data.entities.TransactionEntity
import domain.models.TransactionsMockDTO
import domain.repo.CardDetailsRepository
import glue.card_details.mapper.toTransactionsMockDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CardDetailsRepositoryImpl @Inject constructor(
    private val pager:Pager<Int, TransactionEntity>
):CardDetailsRepository {
    override fun getTransactionsList(): Flow<PagingData<TransactionsMockDTO>> {
        return pager.flow.map { pagingData->
            pagingData.map { transactionEntity->
                transactionEntity.toTransactionsMockDTO()
            }
        }
    }
}