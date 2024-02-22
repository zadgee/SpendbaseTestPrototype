package data.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cards_table"
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val cardId:String,
    val name:String,
    val last4Numbers:String,
    val issuedAt:String,
    val isLocked: Boolean,
    val isTerminated:Boolean,
    val spent:Int,
    val limit:Int,
    val limitType:String,
    val cardHolder: CardHolderEntity
)

data class CardHolderEntity(
    val id:String,
    val name:String,
    val email:String,
    val logoUrl:String,
)