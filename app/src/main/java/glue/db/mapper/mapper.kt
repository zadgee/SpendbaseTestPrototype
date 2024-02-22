package glue.db.mapper

import domain.models.CardDTO
import domain.models.CardHolderMock
import domain.models.CardMockModel
import domain.models.TransactionDTO
import domain.models.TransactionMockModel
import domain.models.UserAccountMockModel

fun CardDTO.toMockModel():CardMockModel{
    return CardMockModel(
        id = id,
        name = name,
        last4Numbers = last4Numbers,
        issuedAt = issuedAt,
        isLocked = isLocked,
        isTerminated = isTerminated,
        spent = spent,
        limit = limit,
        cardHolder = CardHolderMock(
            id = cardHolderDTO.id,
            name = cardHolderDTO.name,
            logoUrl = cardHolderDTO.logoUrl,
            email = cardHolderDTO.email
        ),
        limitType = limitType
    )
}

fun TransactionDTO.toTransactionMockModel():TransactionMockModel{
    return TransactionMockModel(
        id = id,
        publicId = publicId,
        amount = amount,
        type = type,
        status = status,
        origin = origin,
        account = UserAccountMockModel(
            name = account.name,
            type = account.type,
            last4AccountNumbers = account.last4AccountNumbers
        ),
        createdAt = createdAt,
        card = CardMockModel(
            id = card.id,
            name = card.name,
            last4Numbers = card.last4Numbers,
            issuedAt = card.issuedAt,
            isLocked = card.isLocked,
            isTerminated = card.isTerminated,
            spent = card.spent,
            limit = card.limit,
            cardHolder = CardHolderMock(
                id = card.cardHolderDTO.id,
                name = card.cardHolderDTO.name,
                logoUrl = card.cardHolderDTO.logoUrl,
                email = card.cardHolderDTO.email
            ),
            limitType = card.limitType
        )
    )
}