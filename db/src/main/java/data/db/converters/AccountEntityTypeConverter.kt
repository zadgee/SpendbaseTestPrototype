package data.db.converters

import androidx.room.TypeConverter
import data.entities.AccountEntity
import org.json.JSONObject

class AccountEntityTypeConverter {

    @TypeConverter
    fun fromAccountEntity(accountEntity: AccountEntity): String {
        val jsonObject = JSONObject().apply {
            put("name", accountEntity.name)
            put("last4AccountNumbers", accountEntity.last4AccountNumbers)
            put("type", accountEntity.type)
        }
        return jsonObject.toString()
    }

    @TypeConverter
    fun toAccountEntity(accountEntityString: String): AccountEntity {
        val jsonObject = JSONObject(accountEntityString)
        return AccountEntity(
            name = jsonObject.getString("name"),
            last4AccountNumbers = jsonObject.getString("last4AccountNumbers"),
            type = jsonObject.getString("type")
        )
    }
}