package ar.edu.itba.hci.fiit_mobile.data.network

import android.content.Context
import ar.edu.itba.hci.fiit_mobile.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit: Retrofit

    //configuracion del interceptor (ver que esta pasando entre las tareas que hace retorfit)
    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        //canal de comunicacion
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        //gson -> formato de los datos
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getApiRoutineService(): ApiRoutineService {
        return retrofit.create(ApiRoutineService::class.java)
    }

    fun getApiUserService(): ApiUserService{
        return retrofit.create(ApiUserService::class.java)
    }
}