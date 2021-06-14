package ar.com.unlam.marvel_app.data.model.network

import BaseMarvelCharactersResponse
import BaseMarvelEventsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<BaseMarvelCharactersResponse>


    @GET("events")
    suspend fun getAllEvents(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<BaseMarvelEventsResponse>
}