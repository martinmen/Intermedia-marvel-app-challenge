package ar.com.unlam.marvel_app.data.model.network

import android.util.Log
import ar.com.unlam.marvel_app.retrofit.repositories.UtilsApiMarvel
import BaseMarvelCharactersResponse
import ar.com.unlam.marvel_app.data.model.apiModel.BaseMarvelEventsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.MessageDigest

class MarvelServiceImpl {
    private val retrofit = UtilsApiMarvel.getRetrofit()
    val authParams = AuthParams(UtilsApiMarvel.PUBLIC_API_KEY, 1, generateHash())//)md5()

    private fun generateHash(): String = MessageDigest.getInstance("MD5")
        .digest(("1${UtilsApiMarvel.PRIVATE_API_KEY}${UtilsApiMarvel.PUBLIC_API_KEY}").toByteArray())
        .joinToString("") { "%02x".format(it) }

    suspend fun getHeroes(): BaseMarvelCharactersResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MarvelService::class.java).getAllCharacters(
                authParams.apiKey,
                authParams.hash,
                authParams.ts.toString()
            )//,offset,limit
            (response.body()) as BaseMarvelCharactersResponse
        }
    }

    suspend fun getEvents(): BaseMarvelEventsResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MarvelService::class.java).getAllEvents(
                authParams.apiKey,
                authParams.hash,
                authParams.ts.toString()
            )//,offset,limit
            (response.body()) as BaseMarvelEventsResponse
        }
    }

    fun md5(): String {
        var parametros = "1${UtilsApiMarvel.PRIVATE_API_KEY}${UtilsApiMarvel.PUBLIC_API_KEY}"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(parametros.toByteArray())).toString(16).padStart(32, '0')
    }

    class AuthParams(val apiKey: String, val ts: Int, val hash: String) {
        fun getMap(): HashMap<String, String> {
            val hashMap = HashMap<String, String>()
            hashMap["apikey"] = apiKey
            hashMap["ts"] = ts.toString()
            hashMap["hash"] = hash
            return hashMap
        }
    }
}