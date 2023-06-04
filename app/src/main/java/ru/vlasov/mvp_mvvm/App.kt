package ru.vlasov.mvp_mvvm

import android.app.Application
import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vlasov.mvp_mvvm.data.FoxRepositoryImpl
import ru.vlasov.mvp_mvvm.data.remote.FoxApi
import ru.vlasov.mvp_mvvm.domain.FoxRepository

class App : Application() {

    private val foxApi: FoxApi by lazy {
        Retrofit.Builder()
        .baseUrl("https://randomfox.ca/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoxApi::class.java)
    }
    val foxRepository: FoxRepository by lazy {
        FoxRepositoryImpl(
            foxApi
        )
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }