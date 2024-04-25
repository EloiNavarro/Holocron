package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.BuildConfig
import com.eloinavarro.holocron.data.retrofit.services.CharactersService
import com.eloinavarro.holocron.data.retrofit.services.MoviesService
import com.eloinavarro.holocron.data.retrofit.services.PlanetsService
import com.eloinavarro.holocron.data.retrofit.services.SpeciesService
import com.eloinavarro.holocron.data.retrofit.services.StarshipsService
import com.eloinavarro.holocron.data.retrofit.services.VehiclesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object SwapiRetrofitClient {

    private const val BASE_URL = "https://swapi.dev/api/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
//        .client(okHttpClient)
        .client(unworthyHttpClient())
        .build()

    val charactersService: CharactersService = getRetrofit().create()
    val moviesService: MoviesService = getRetrofit().create()
    val planetsService: PlanetsService = getRetrofit().create()
    val speciesService: SpeciesService = getRetrofit().create()
    val starshipsService: StarshipsService = getRetrofit().create()
    val vehiclesService: VehiclesService = getRetrofit().create()

    private fun untrustworthyManager(): Array<TrustManager> = listOf(
        object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
        }
    ).toTypedArray()

    private fun unworthyHttpClient(): OkHttpClient {
        val sslContext = SSLContext.getInstance("SSL")
        val untrustworthyManager = untrustworthyManager()
        sslContext.init(null, untrustworthyManager, SecureRandom())
        val sslSocketFactory = sslContext.socketFactory
        return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, untrustworthyManager[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .build()
    }
}