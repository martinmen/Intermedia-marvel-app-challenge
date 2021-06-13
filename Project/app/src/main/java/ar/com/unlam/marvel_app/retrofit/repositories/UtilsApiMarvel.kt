package ar.com.unlam.marvel_app.retrofit.repositories

import android.provider.Settings.Global.getString
import ar.com.unlam.marvel_app.data.model.network.MarvelService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest

object UtilsApiMarvel {

    const val BASE_API_URL = "https://gateway.marvel.com/v1/public/"
    const val PRIVATE_API_KEY = "a1f5fab9977c32b240ddc5c278c9224c770f7a42"
    const val PUBLIC_API_KEY = "23cbc3ddead5db37b760a3f8b1ea6b9c"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun getRetro(): MarvelService {
        val service: MarvelService = Retrofit.Builder()
            .baseUrl("https://apis.datos.gob.ar")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelService::class.java)
        return service
    }

    val authParams = AuthParams(PUBLIC_API_KEY, 1, generateHash())

    class AuthParams(private val apiKey: String, private val ts: Int, private val hash: String) {
        fun getMap(): HashMap<String, String> {
            val hashMap = HashMap<String, String>()
            hashMap["apikey"] = apiKey
            hashMap["ts"] = ts.toString()
            hashMap["hash"] = hash
            return hashMap
        }
    }

    private fun generateHash(): String = MessageDigest.getInstance("MD5")
        .digest(("1$PRIVATE_API_KEY$PUBLIC_API_KEY").toByteArray())
        .joinToString("") { "%02x".format(it) }

}