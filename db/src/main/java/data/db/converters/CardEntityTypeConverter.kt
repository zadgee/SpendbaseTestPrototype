package data.db.converters

import androidx.room.TypeConverter
import data.entities.CardEntity
import data.entities.CardHolderEntity
import org.json.JSONObject

class CardEntityTypeConverter {

    @TypeConverter
    fun fromCardEntity(cardEntity: CardEntity): String {
        val jsonObject = JSONObject().apply {
            put("id", cardEntity.id)
            put("cardId", cardEntity.cardId)
            put("name", cardEntity.name)
            put("last4Numbers", cardEntity.last4Numbers)
            put("issuedAt", cardEntity.issuedAt)
            put("isLocked", cardEntity.isLocked)
            put("isTerminated", cardEntity.isTerminated)
            put("spent", cardEntity.spent)
            put("limit", cardEntity.limit)
            put("limitType", cardEntity.limitType)
            put("cardHolder", JSONObject().apply {
                put("id", cardEntity.cardHolder.id)
                put("name", cardEntity.cardHolder.name)
                put("email", cardEntity.cardHolder.email)
                put("logoUrl", cardEntity.cardHolder.logoUrl)
            })
        }
        return jsonObject.toString()
    }

    @TypeConverter
    fun toCardEntity(cardEntityString: String): CardEntity {
        val jsonObject = JSONObject(cardEntityString)
        val cardHolderJson = jsonObject.getJSONObject("cardHolder")
        return CardEntity(
            id = jsonObject.getInt("id"),
            cardId = jsonObject.getString("cardId"),
            name = jsonObject.getString("name"),
            last4Numbers = jsonObject.getString("last4Numbers"),
            issuedAt = jsonObject.getString("issuedAt"),
            isLocked = jsonObject.getBoolean("isLocked"),
            isTerminated = jsonObject.getBoolean("isTerminated"),
            spent = jsonObject.getInt("spent"),
            limit = jsonObject.getInt("limit"),
            limitType = jsonObject.getString("limitType"),
            cardHolder = CardHolderEntity(
                id = cardHolderJson.getString("id"),
                name = cardHolderJson.getString("name"),
                email = cardHolderJson.getString("email"),
                logoUrl = cardHolderJson.getString("logoUrl")
            )
        )
    }
}