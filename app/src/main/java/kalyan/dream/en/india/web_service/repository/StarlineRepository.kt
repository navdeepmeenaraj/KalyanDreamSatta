package kalyan.dream.en.india.web_service.repository

import kalyan.dream.en.india.web_service.api.ApiHelper
import javax.inject.Inject

class StarlineRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getStarMarkets(token: String) =
        apiHelper.provideStarMarkets(token)

    suspend fun getStarRates(token: String) =
        apiHelper.provideStarRates(token)

    suspend fun getStarBids(token: String, userId: Int) =
        apiHelper.provideStarBids(token, userId)

    suspend fun getStarWins(token: String, userId: Int) =
        apiHelper.provideStarWins(token, userId)

    suspend fun getCurrentDate() = apiHelper.provideCurrentDate()
    suspend fun placeStarBid(token: String, map: HashMap<String, Any?>) =
        apiHelper.provideStarBidPlace(token, map)
}