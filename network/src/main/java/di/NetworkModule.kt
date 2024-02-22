package di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.service.CardsService
import data.impl.RemoteDataSourceImpl
import data.service.TransactionsService
import domain.repo.RemoteDataSource
import domain.usecases.GetCardsUseCase
import domain.usecases.GetTransactionsUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkhttpClientForCards(
        interceptor: HttpLoggingInterceptor
    ):OkHttpClient{
       return OkHttpClient
           .Builder()
           .addInterceptor(interceptor)
           .build()
    }

    @Provides
    @CardsUrl
    @Singleton
    fun provideCardsUrlForRetrofit(
        client: OkHttpClient
    ):Retrofit{
        return Retrofit
            .Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dev.spendbase.co/")
            .build()
    }

    @Provides
    @TransactionsUrl
    @Singleton
    fun provideTransactionsUrlForRetrofit(
        client: OkHttpClient
    ):Retrofit{
        return Retrofit
            .Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dev.spendbase.co/cards/")
            .build()
    }

    @Provides
    @Singleton
    fun provideCardsService(
       @CardsUrl retrofit: Retrofit
    ): CardsService {
        return retrofit.create(CardsService::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionsService(
        @TransactionsUrl retrofit: Retrofit
    ): TransactionsService {
        return retrofit.create(TransactionsService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        cardsService: CardsService,
        transactionsService: TransactionsService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            cardsApi = cardsService,
            transactionsApi = transactionsService
        )
    }


    @Provides
    @Singleton
    fun provideGetTransactionsUseCase(
        remoteDataSource: RemoteDataSource
    ): GetTransactionsUseCase{
        return GetTransactionsUseCase(
            remoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideGetCardPageUseCase(
        remoteDataSource: RemoteDataSource
    ): GetCardsUseCase {
        return GetCardsUseCase(
            remoteDataSource
        )
    }


}