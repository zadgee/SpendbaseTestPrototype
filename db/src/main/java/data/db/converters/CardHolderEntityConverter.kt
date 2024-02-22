package data.db.converters

import androidx.room.TypeConverter
import data.entities.CardHolderEntity
import org.json.JSONObject

class CardHolderEntityConverter {

    @TypeConverter
    fun fromCardHolderEntity(cardHolderEntity: CardHolderEntity): String {
        val jsonObject = JSONObject().apply {
            put("id", cardHolderEntity.id)
            put("name", cardHolderEntity.name)
            put("email", cardHolderEntity.email)
            put("logoUrl", cardHolderEntity.logoUrl)
        }
        return jsonObject.toString()
    }

    @TypeConverter
    fun toCardHolderEntity(cardHolderEntityString: String): CardHolderEntity {
        val jsonObject = JSONObject(cardHolderEntityString)
        return CardHolderEntity(
            id = jsonObject.getString("id"),
            name = jsonObject.getString("name"),
            email = jsonObject.getString("email"),
            logoUrl = jsonObject.getString("logoUrl")
        )
    }

}