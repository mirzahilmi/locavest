package id.my.miruza.locavest

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object RetrofitInstance {
    val api: SuperAPI by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .baseUrl("https://example.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SuperAPI::class.java)
    }

}