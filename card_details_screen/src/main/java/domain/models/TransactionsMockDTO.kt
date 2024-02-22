package domain.models

data class TransactionsMockDTO(
    val id:String,
    val publicId:String,
    val amount:Double,
    val type:String,
    val status:String,
    val origin:String,
    val account: UserAccountMockDTO,
    val createdAt:String,
    val card: CardMockDTO?
)

data class UserAccountMockDTO(
    val name:String,
    val last4AccountNumbers:String,
    val type:String
)

data class CardMockDTO(
    val id:String,
    val name:String,
    val last4Numbers:String,
    val issuedAt:String,
    val isLocked: Boolean,
    val isTerminated:Boolean,
    val spent:Int,
    val limit:Int,
    val limitType:String,
    val cardHolder: CardHolderMockDTO
)

data class CardHolderMockDTO(
    val id:String,
    val name:String,
    val logoUrl:String,
    val email:String
)
