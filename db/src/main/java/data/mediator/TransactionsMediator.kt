package data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import data.db.AppDatabase
import data.entities.TransactionEntity
import data.mapper.toTransactionEntity
import domain.repository.FinancialRepository
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TransactionsMediator @Inject constructor(
    private val db: AppDatabase,
    private val repository: FinancialRepository
): RemoteMediator<Int, TransactionEntity>(){

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TransactionEntity>
    ): MediatorResult {
        return try {
            val loadKey:Int = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null){
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            val transactions = repository.getTransactionsPage(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            db.withTransaction {
                if(loadType == LoadType.REFRESH){
                    db.dao().clearTransactions()
                }
                val transactionEntities = transactions.map { it.toTransactionEntity() }
                db.dao().upsertTransactions(transactionEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = transactions.isEmpty()
            )
        } catch (e: IOException){
            MediatorResult.Error(e)
        }
    }
}