package data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import data.db.AppDatabase
import data.entities.CardEntity
import data.mapper.toCardEntity
import domain.repository.FinancialRepository
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CardsMediator @Inject constructor(
    private val db: AppDatabase,
    private val repository: FinancialRepository
): RemoteMediator<Int, CardEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CardEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
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
            val cards = repository.getCardPage(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            db.withTransaction {
                if(loadType == LoadType.REFRESH){
                    db.dao().clearCards()
                }
                val cardEntities = cards.map { it.toCardEntity() }
                db.dao().upsertCards(cardEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = cards.isEmpty()
            )
        } catch (e: IOException){
            MediatorResult.Error(e)
        }
    }


}