package domain.models

data class CardDTO(
    val id:String,
    val name:String,
    val last4Numbers:String,
    val issuedAt:String,
    val isLocked: Boolean,
    val isTerminated:Boolean,
    val spent:Int,
    val limit:Int,
    val limitType:String,
    val cardHolderDTO: CardHolderDTO
)

data class CardHolderDTO(
    val id:String,
    val name:String,
    val logoUrl:String,
    val email:String
)
