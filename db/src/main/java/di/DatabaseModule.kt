package di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import data.dao.FinancialDAO
import data.db.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
     @Provides
     @Singleton
     fun provideRoomDataBaseInstance(
         @ApplicationContext context:Context
     ): AppDatabase {
         return Room.databaseBuilder(
             context,
             AppDatabase::class.java,
             "app_database"
         ).addMigrations(AppDatabase.migration3To4)
             .build()
     }

    @Provides
    @Singleton
    fun provideFinancialDao(
        db:AppDatabase
    ):FinancialDAO{
        return db.dao()
    }

}