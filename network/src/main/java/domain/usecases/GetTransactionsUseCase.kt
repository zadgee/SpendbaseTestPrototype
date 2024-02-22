package domain.usecases
import domain.models.CardDTO
import domain.models.CardHolderDTO
import domain.models.TransactionDTO
import domain.models.UserAccountDTO
import domain.repo.RemoteDataSource
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun retrieve(
        page:Int?=null,
        pageCount:Int?=null
    ):List<TransactionDTO>{
        val response = remoteDataSource.getTransactionsList(page, pageCount)
        return if(response.isSuccessful){
            response.body()?.transactions?.map { transactionModel ->
                TransactionDTO(
                    id = transactionModel.id,
                    publicId = transactionModel.publicId,
                    amount = transactionModel.amount,
                    createdAt = transactionModel.createdAt,
                    type = transactionModel.type,
                    status = transactionModel.status,
                    origin = transactionModel.origin,
                    account = UserAccountDTO(
                        name = transactionModel.account.name,
                        last4AccountNumbers = transactionModel.account.last4AccountNumbers,
                        type = transactionModel.account.type
                    ),
                    card = CardDTO(
                        id = transactionModel.card?.id?:"",
                        name = transactionModel.card?.name?:"",
                        last4Numbers = transactionModel.card?.last4Numbers?:"",
                        issuedAt = transactionModel.card?.issuedAt?:"",
                        isLocked = transactionModel.card?.isLocked?:false,
                        isTerminated = transactionModel.card?.isTerminated?:false,
                        spent = transactionModel.card?.spent?:-1000,
                        limit = transactionModel.card?.limit?:-1000,
                        limitType = transactionModel.card?.limitType?:"",
                        cardHolderDTO = CardHolderDTO(
                            id = transactionModel.card?.cardHolder?.id?:"",
                            name = transactionModel.card?.cardHolder?.name?:"",
                            email = transactionModel.card?.cardHolder?.email?:"",
                            logoUrl = transactionModel.card?.cardHolder?.logoUrl?:""
                        )
                    )
                )
            }?: emptyList()
        } else {
            throw Exception(response.message())
        }
    }

}