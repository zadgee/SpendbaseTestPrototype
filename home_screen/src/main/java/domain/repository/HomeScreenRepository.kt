package domain.repository

import androidx.paging.PagingData
import domain.models.CreditCardInfo
import domain.models.TransactionInfo
import kotlinx.coroutines.flow.Flow

interface HomeScreenRepository{
    fun cardPagerProvider():Flow<PagingData<CreditCardInfo>>
    fun transactionPagerProvider():Flow<PagingData<TransactionInfo>>
}