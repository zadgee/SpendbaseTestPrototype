package glue.db.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import domain.repository.FinancialRepository
import domain.usecases.GetCardsUseCase
import domain.usecases.GetTransactionsUseCase
import glue.db.repo.FinancialRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
class FinancialModule {

    @Provides
    @ViewModelScoped
    fun provideFinancialRepository(
        getCardsUseCase: GetCardsUseCase,
        getTransactionsPageUseCase: GetTransactionsUseCase
    ): FinancialRepository {
        return FinancialRepositoryImpl(getCardsUseCase, getTransactionsPageUseCase)
    }

}