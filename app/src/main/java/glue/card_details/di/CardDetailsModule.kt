package glue.card_details.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import data.dao.FinancialDAO
import data.db.AppDatabase
import data.entities.TransactionEntity
import data.mediator.TransactionsMediator
import domain.repo.CardDetailsRepository
import domain.repository.FinancialRepository
import glue.card_details.di.annotations.PagerListTransactionEntity
import glue.card_details.repo.CardDetailsRepositoryImpl

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(ViewModelComponent::class)
class CardDetailsModule {


    @Provides
    @PagerListTransactionEntity
    @ViewModelScoped
    fun providePager(
        dao:FinancialDAO,
        db:AppDatabase,
        repository:FinancialRepository
    ):Pager<Int, TransactionEntity>{
        return Pager(
            pagingSourceFactory = {
                dao.transactionsPagingSource()
            },
            config = PagingConfig(7),
            remoteMediator = TransactionsMediator(
                repository = repository,
                db = db
            )
        )
    }

    @Provides
    @ViewModelScoped
    fun provideCardDetailsRepository(
        @PagerListTransactionEntity pager:Pager<Int, TransactionEntity>
    ): CardDetailsRepository {
        return CardDetailsRepositoryImpl(
            pager
        )
    }

}