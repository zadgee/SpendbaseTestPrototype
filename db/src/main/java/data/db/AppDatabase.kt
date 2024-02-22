package data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import data.entities.CardEntity
import data.dao.FinancialDAO
import data.db.converters.AccountEntityTypeConverter
import data.db.converters.CardEntityTypeConverter
import data.db.converters.CardHolderEntityConverter
import data.entities.TransactionEntity

@Database(
    entities = [
        CardEntity::class,
        TransactionEntity::class
    ],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1, to = 2, spec = AppDatabase.AutoMigrationFrom1To2::class
        )
    ]
)
@TypeConverters(
    CardHolderEntityConverter::class,
    AccountEntityTypeConverter::class,
    CardEntityTypeConverter::class
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun dao(): FinancialDAO

    class AutoMigrationFrom1To2: AutoMigrationSpec
    companion object{
        val migration3To4 = object :Migration(2,3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE transactions_table RENAME TO transactions_table_temp")
                db.execSQL("CREATE TABLE IF NOT EXISTS transactions_table " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "transactionId TEXT NOT NULL, " +
                        "publicId TEXT NOT NULL, " +
                        "amount REAL NOT NULL, " +
                        "type TEXT NOT NULL, " +
                        "status TEXT NOT NULL, " +
                        "origin TEXT NOT NULL, " +
                        "account TEXT NOT NULL, " +
                        "createdAt TEXT NOT NULL, " +
                        "card TEXT)")
                db.execSQL("INSERT INTO transactions_table " +
                        "(id, transactionId, publicId, amount, type, status, origin, account, createdAt, card) " +
                        "SELECT id, transactionId, publicId, CAST(amount AS REAL), type, status, origin, account, createdAt, card " +
                        "FROM transactions_table_temp")
                db.execSQL("DROP TABLE transactions_table_temp")
            }

        }
    }
}