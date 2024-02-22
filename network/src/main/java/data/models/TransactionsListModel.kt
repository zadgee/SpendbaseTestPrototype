package data.models

import com.google.gson.annotations.SerializedName

data class TransactionsListModel(
    val transactions: List<TransactionModel>
)

data class TransactionModel(
    val id:String,
    val publicId:String,
    val amount:Double,
    val type:String,
    val status:String,
    val origin:String,
    val account: UserAccountModel,
    @SerializedName("createDate")
    val createdAt:String,
    val card: CardModel?
)


data class UserAccountModel(
    @SerializedName("accountName")
    val name:String,
    @SerializedName("accountLast4")
    val last4AccountNumbers:String,
    @SerializedName("accountType")
    val type:String
)