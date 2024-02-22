package glue.card_details.mapper

import data.entities.TransactionEntity
import domain.models.CardHolderMockDTO
import domain.models.CardMockDTO
import domain.models.TransactionsMockDTO
import domain.models.UserAccountMockDTO

fun TransactionEntity.toTransactionsMockDTO():TransactionsMockDTO{
    return TransactionsMockDTO(
        id = transactionId,
        type = type,
        amount = amount,
        status = status,
        publicId = publicId,
        origin = origin,
        account = UserAccountMockDTO(
          name = account.name,
          last4AccountNumbers = account.last4AccountNumbers,
          type = account.type
        ),
        createdAt = createdAt,
        card = CardMockDTO(
            id = card?.cardId?:"",
            isLocked = card?.isLocked?:false,
            issuedAt = card?.issuedAt?:"",
            isTerminated = card?.isTerminated?:false,
            last4Numbers = card?.last4Numbers?:"",
            limit = card?.limit?:0,
            limitType = card?.limitType?:"",
            name = card?.name?:"",
            spent = card?.spent?:0,
            cardHolder = CardHolderMockDTO(
                email = card?.cardHolder?.email?:"",
                name = card?.cardHolder?.name?:"",
                logoUrl = card?.cardHolder?.logoUrl?:"",
                id = card?.cardHolder?.id?:""
            )
        ),
    )
}