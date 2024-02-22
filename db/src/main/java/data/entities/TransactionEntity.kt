package data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions_table"
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val transactionId: String,
    val publicId: String,
    val amount:Double, // changed data type
    val type: String,
    val status: String,
    val origin: String,
    val account: AccountEntity,
    val createdAt: String,
    val card: CardEntity?
)

data class AccountEntity(
    val name:String,
    val last4AccountNumbers:String,
    val type:String
)