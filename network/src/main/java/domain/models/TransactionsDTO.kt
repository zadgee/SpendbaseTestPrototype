package domain.models

data class TransactionDTO(
    val id:String,
    val publicId:String,
    val amount:Double,
    val type:String,
    val status:String,
    val origin:String,
    val account:UserAccountDTO,
    val createdAt:String,
    val card:CardDTO,
)

data class UserAccountDTO(
    val name:String,
    val last4AccountNumbers:String,
    val type:String
)