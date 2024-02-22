package data.mapper
import data.entities.AccountEntity
import data.entities.CardEntity
import data.entities.CardHolderEntity
import data.entities.TransactionEntity
import domain.models.CardMockModel
import domain.models.TransactionMockModel

fun CardMockModel.toCardEntity():CardEntity{
    return CardEntity(
        cardId = id,
        name = name,
        last4Numbers = last4Numbers,
        issuedAt = issuedAt,
        isLocked = isLocked,
        isTerminated = isTerminated,
        spent = spent,
        limit = limit,
        limitType = limitType,
        cardHolder = CardHolderEntity(
            id = cardHolder.id,
            name = cardHolder.name,
            email = cardHolder.email,
            logoUrl = cardHolder.logoUrl
        )
    )
}

fun TransactionMockModel.toTransactionEntity():TransactionEntity{
    return TransactionEntity(
        transactionId = id,
        amount = amount,
        origin = origin,
        publicId = publicId,
        type = type,
        status = status,
        createdAt = createdAt,
        account = AccountEntity(
            name = account.name,
            last4AccountNumbers = account.last4AccountNumbers,
            type = account.type
        ),
        card = CardEntity(
            cardHolder = CardHolderEntity(
                id = card.cardHolder.id,
                name = card.cardHolder.name,
                email = card.cardHolder.email,
                logoUrl = card.cardHolder.logoUrl
            ),
            name = card.name,
            last4Numbers = card.last4Numbers,
            issuedAt = card.issuedAt,
            isLocked = card.isLocked,
            isTerminated = card.isTerminated,
            spent = card.spent,
            limit = card.limit,
            limitType = card.limitType,
            cardId = id
        ),
    )
}