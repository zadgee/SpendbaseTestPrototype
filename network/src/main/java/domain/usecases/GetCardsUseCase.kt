package domain.usecases
import domain.models.CardDTO
import domain.models.CardHolderDTO
import domain.repo.RemoteDataSource
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun retrieve(
        page:Int? = null,
        pageCount:Int? = null
    ):List<CardDTO>{
       val response = remoteDataSource.getCardPage(page, pageCount)
        return if(response.isSuccessful){
            response.body()?.cards?.map { cardModel ->
                CardDTO(
                    id = cardModel.id,
                    name = cardModel.name,
                    last4Numbers = cardModel.last4Numbers,
                    issuedAt = cardModel.issuedAt,
                    isLocked = cardModel.isLocked,
                    isTerminated = cardModel.isTerminated,
                    spent = cardModel.spent,
                    limit = cardModel.limit,
                    limitType = cardModel.limitType,
                    cardHolderDTO = CardHolderDTO(
                        id = cardModel.cardHolder.id,
                        name = cardModel.cardHolder.name,
                        email = cardModel.cardHolder.email,
                        logoUrl = cardModel.cardHolder.logoUrl
                    )
                )
            } ?: emptyList()
        } else {
             throw Exception(response.message())
        }
    }

}