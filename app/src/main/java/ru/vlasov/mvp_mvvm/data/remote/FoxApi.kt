package ru.vlasov.mvp_mvvm.data.remote

import retrofit2.Call
import retrofit2.http.GET
import ru.vlasov.mvp_mvvm.data.remote.model.FoxDto

interface FoxApi {

    @GET("floof")
    fun getFox(): Call<FoxDto>
}