package ar.com.unlam.marvel_app.data.model.network

import android.util.Log
import ar.com.unlam.marvel_app.retrofit.repositories.UtilsApiMarvel
import BaseMarvelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.MessageDigest

class MarvelServiceImpl {
    private val retrofit = UtilsApiMarvel.getRetrofit()
    val authParams = AuthParams(UtilsApiMarvel.PUBLIC_API_KEY, 1, generateHash())//)md5()

    class AuthParams(val apiKey: String, val ts: Int, val hash: String) {
        fun getMap(): HashMap<String, String> {
            val hashMap = HashMap<String, String>()
            hashMap["apikey"] = apiKey
            hashMap["ts"] = ts.toString()
            hashMap["hash"] = hash
            return hashMap
        }
    }

    private fun generateHash(): String = MessageDigest.getInstance("MD5")
        .digest(("1${UtilsApiMarvel.PRIVATE_API_KEY}${UtilsApiMarvel.PUBLIC_API_KEY}").toByteArray())
        .joinToString("") { "%02x".format(it) }

    suspend fun getHeroes(): BaseMarvelResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MarvelService::class.java).getAllHeroes(
                authParams.apiKey,
                authParams.hash,
                authParams.ts.toString()
            )//,offset,limit
            Log.d("response", response.toString())
            (response.body()) as BaseMarvelResponse
        }
    }

    fun md5(): String {
        var parametros = "1${UtilsApiMarvel.PRIVATE_API_KEY}${UtilsApiMarvel.PUBLIC_API_KEY}"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(parametros.toByteArray())).toString(16).padStart(32, '0')
    }


}