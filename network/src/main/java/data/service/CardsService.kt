package data.service
import data.models.CardsListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CardsService {

    @GET("cards")
    suspend fun getCardPage(
        @Query("page") page:Int?=null,
        @Query("per_page") pageCount:Int?=null
    ):Response<CardsListModel>
}