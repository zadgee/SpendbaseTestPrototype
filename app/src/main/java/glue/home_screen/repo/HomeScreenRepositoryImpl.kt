package glue.home_screen.repo

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import data.entities.CardEntity
import data.entities.TransactionEntity
import domain.models.CreditCardInfo
import domain.models.TransactionInfo
import domain.repository.HomeScreenRepository
import glue.home_screen.di.annotations.PagerCardEntity
import glue.home_screen.di.annotations.PagerTransactionEntity
import glue.home_screen.mapper.toCreditCardInfo
import glue.home_screen.mapper.toTransactionInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeScreenRepositoryImpl @Inject constructor(
    @PagerCardEntity private val cardPager:Pager<Int,CardEntity>,
    @PagerTransactionEntity private val transactionPager:Pager<Int,TransactionEntity>
):HomeScreenRepository {
    override fun cardPagerProvider(): Flow<PagingData<CreditCardInfo>> {
        return cardPager.flow.map { pagingData->
            pagingData.map { cardEntity->
                cardEntity.toCreditCardInfo()
            }
        }
    }

    override fun transactionPagerProvider(): Flow<PagingData<TransactionInfo>> {
        return transactionPager.flow.map {pagingData->
            pagingData.map { transactionEntity->
                transactionEntity.toTransactionInfo()
            }
        }
        }
    }
