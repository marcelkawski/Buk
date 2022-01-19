package pl.android.buk.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

object Rest {
    private lateinit var serviceRest: RestInterface
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var gson: Gson
    val interceptor = HttpLoggingInterceptor()

    fun getOkClient(): OkHttpClient {
        return okHttpClient
    }

    fun getGson(): Gson {
        return gson
    }

    fun getRest(): RestInterface {
        return serviceRest
    }

    fun init() {
        gson = GsonBuilder().create()
        okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        serviceRest = retrofit.create(RestInterface::class.java)
    }

    fun cancel(call: Call<*>) {
        try {
            if (!call.isCanceled && call.isExecuted) {
                call.cancel()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}