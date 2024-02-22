package glue.home_screen.mapper

import data.entities.CardEntity
import data.entities.TransactionEntity
import domain.models.CreditCardHolder
import domain.models.CreditCardInfo
import domain.models.TransactionInfo
import domain.models.UserAccountInfo

fun CardEntity.toCreditCardInfo():CreditCardInfo{
    return CreditCardInfo(
        id = cardId,
        name = name,
        last4Numbers = last4Numbers,
        issuedAt = issuedAt,
        isTerminated = isTerminated,
        isLocked = isLocked,
        spent = spent,
        limit = limit,
        limitType = limitType,
        cardHolder = CreditCardHolder(
            id = cardHolder.id,
            name = cardHolder.name,
            logoUrl = cardHolder.logoUrl,
            email = cardHolder.email
        )
    )
}

fun TransactionEntity.toTransactionInfo(

):TransactionInfo{
    return TransactionInfo(
        id = transactionId,
        amount = amount,
        publicId = publicId,
        type = type,
        status = status,
        origin = origin,
        createdAt = createdAt,
        account = UserAccountInfo(
            name = account.name,
            type = account.type,
            last4AccountNumbers = account.last4AccountNumbers
        ),
        card = CreditCardInfo(
            id = card?.cardId?:"",
            name = card?.name?:"",
            last4Numbers = card?.last4Numbers?:"",
            issuedAt = card?.issuedAt?:"",
            isLocked = card?.isLocked?:false,
            spent = card?.spent?:0,
            limit = card?.limit?:0,
            limitType = card?.limitType?:"",
            isTerminated = card?.isTerminated?:false,
            cardHolder = CreditCardHolder(
                id = card?.cardHolder?.id?:"",
                name = card?.cardHolder?.name?:"",
                logoUrl = card?.cardHolder?.logoUrl?:"",
                email = card?.cardHolder?.email?:""
            )
        )
    )
}