package domain.models

data class TransactionMockModel(
    val id:String,
    val publicId:String,
    val amount:Double,
    val type:String,
    val status:String,
    val origin:String,
    val account:UserAccountMockModel,
    val createdAt:String,
    val card:CardMockModel
)

data class UserAccountMockModel(
    val name:String,
    val last4AccountNumbers:String,
    val type:String
)