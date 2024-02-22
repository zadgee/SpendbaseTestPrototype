package domain.models

data class TransactionInfo(
    val id:String,
    val publicId:String,
    val amount:Double,
    val type:String,
    val status:String,
    val origin:String,
    val account: UserAccountInfo,
    val createdAt:String,
    val card: CreditCardInfo?
)

data class UserAccountInfo(
    val name:String,
    val type: String,
    val last4AccountNumbers:String
)
