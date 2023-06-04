package ru.vlasov.mvp_mvvm.data

import android.os.Handler
import android.os.Looper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.vlasov.mvp_mvvm.data.mapper.toFoxEntity
import ru.vlasov.mvp_mvvm.data.remote.FoxApi
import ru.vlasov.mvp_mvvm.data.remote.model.FoxDto
import ru.vlasov.mvp_mvvm.domain.FoxRepository
import ru.vlasov.mvp_mvvm.domain.ResultFox
import ru.vlasov.mvp_mvvm.domain.entities.FoxEntity

class FoxRepositoryImpl(
    private val api: FoxApi
) : FoxRepository {

    override fun getFox(callback: (ResultFox<FoxEntity>) -> Unit) {
        api.getFox().enqueue(object : Callback<FoxDto> {
            override fun onResponse(call: Call<FoxDto>, response: Response<FoxDto>) {
                if (response.isSuccessful) {
                    callback(ResultFox.Success(response.body()!!.toFoxEntity()))
                } else {
                    callback(ResultFox.Error("Ошибка загрузки"))
                }
            }

            override fun onFailure(call: Call<FoxDto>, t: Throwable) {
                callback(ResultFox.Error("Ошибка загрузки"))
            }
        })
    }
}