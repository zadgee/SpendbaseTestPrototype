package data.models

import com.google.gson.annotations.SerializedName

data class CardsListModel(
    val cards:List<CardModel>
)

data class CardModel(
    val id:String,
    @SerializedName("cardName")
    val name:String,
    @SerializedName("cardLast4")
    val last4Numbers:String,
    val issuedAt:String,
    val isLocked: Boolean,
    val isTerminated:Boolean,
    val spent:Int,
    val limit:Int,
    val limitType:String,
    val cardHolder: CardHolderModel
)

data class CardHolderModel(
    val id:String,
    @SerializedName("fullName")
    val name:String,
    val logoUrl:String,
    val email:String
)
