package data.service

import data.models.TransactionsListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TransactionsService {

    @GET("transactions")
    suspend fun getTransactionsList(
        @Query("page") page:Int?=null,
        @Query("per_page") pageCount:Int?=null
    ): Response<TransactionsListModel>

}