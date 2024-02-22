package glue.home_screen.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.app.spendbaseprototype.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import data.dao.FinancialDAO
import data.db.AppDatabase
import data.entities.CardEntity
import data.entities.TransactionEntity
import data.mediator.CardsMediator
import data.mediator.TransactionsMediator
import domain.repository.FinancialRepository
import domain.repository.HomeScreenRepository
import domain.router.HomeRouter
import glue.home_screen.di.annotations.HomeToTransactions
import glue.home_screen.di.annotations.PagerCardEntity
import glue.home_screen.di.annotations.PagerTransactionEntity
import glue.home_screen.repo.HomeScreenRepositoryImpl
import glue.home_screen.router.HomeRouterImpl

@Module
@OptIn(ExperimentalPagingApi::class)
@InstallIn(ViewModelComponent::class)
class HomeModule {


    @Provides
    @HomeToTransactions
    fun provideHomeToTransactionsActionId():Int{
        return R.id.action_homeFragment_to_transactionsFragment
    }

    @Provides
    @ViewModelScoped
    fun provideHomeRouter(
        @HomeToTransactions actionId:Int
    ):HomeRouter{
        return HomeRouterImpl(
            actionId = actionId
        )
    }

    @Provides
    @PagerCardEntity
    @ViewModelScoped
    fun provideCardPager(
        db:AppDatabase,
        repository: FinancialRepository,
        dao: FinancialDAO
    ): Pager<Int, CardEntity> {
        return Pager(
            config = PagingConfig(pageSize = 3),
            remoteMediator = CardsMediator(
                db = db,
                repository = repository
            ),
            pagingSourceFactory = {
                dao.cardsPagingSource()
            }
        )
    }


    @Provides
    @PagerTransactionEntity
    @ViewModelScoped
    fun provideTransactionPager(
        db:AppDatabase,
        repository: FinancialRepository,
        dao: FinancialDAO
    ):Pager<Int,TransactionEntity>{
        return Pager(
            config = PagingConfig(pageSize = 3),
            remoteMediator = TransactionsMediator(
                db = db,
                repository = repository
            ),
            pagingSourceFactory = {
                dao.transactionsPagingSource()
            }
        )
    }

    @Provides
    @ViewModelScoped
    fun provideHomeScreenRepository(
        @PagerCardEntity cardPager:Pager<Int,CardEntity>,
        @PagerTransactionEntity transactionPager:Pager<Int,TransactionEntity>
    ):HomeScreenRepository{
        return HomeScreenRepositoryImpl(
            cardPager = cardPager,
            transactionPager = transactionPager
        )
    }

}