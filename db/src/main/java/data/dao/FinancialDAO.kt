package data.dao
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import data.entities.TransactionEntity
import data.entities.CardEntity

@Dao
interface FinancialDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCards(cards:List<CardEntity>)

    @Query("SELECT * FROM cards_table")
    fun cardsPagingSource():PagingSource<Int, CardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTransactions(transactions:List<TransactionEntity>)

    @Query("SELECT * FROM transactions_table")
    fun transactionsPagingSource():PagingSource<Int, TransactionEntity>

    @Query("DELETE FROM transactions_table")
    suspend fun clearTransactions()

    @Query("DELETE FROM cards_table")
    suspend fun clearCards()
}