package com.example.k_pop

import com.example.k_pop.model.KPopModel
import com.example.k_pop.model.SongApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongRepository(private val songApi: SongApi) {

    interface RepositoryCallback {
        fun onSuccess(model: KPopModel)
        fun onFailure(t: Throwable)
    }

    fun getSong(songName: String, callback: RepositoryCallback) {
        songApi.getSong(songName).enqueue(object : Callback<KPopModel> {
            override fun onResponse(call: Call<KPopModel>, response: Response<KPopModel>) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onFailure(Throwable("Ошибка: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<KPopModel>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}