package ar.com.unlam.marvel_app.data.model.network

import ar.com.unlam.marvel_app.data.model.Hero
import ar.com.unlam.marvel_app.retrofit.repositories.UtilsApiMarvel
import baseResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.security.MessageDigest

interface MarvelService {

    //@QueryMap auth: HashMap<String, String>,
    // @Query("offset") offset: Int,
    //  @Query("limit") limit: Int
    @GET("characters")
    suspend fun getAllHeroes(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response <List<baseResponse>>
}