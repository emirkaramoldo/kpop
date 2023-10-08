package com.example.k_pop.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SongApi {
    @GET("songs")
    fun getSong(
        @Query("q") songsName: String,
        @Query("by") by: String = "Song Name",
        @Header("X-RapidAPI-Key") key: String = "d932152decmsh4e34dcd5a601766p13cc13jsnb5a38bb5d900",
        @Header("X-RapidAPI-Host") host: String = "k-pop.p.rapidapi.com"
    ): Call<KPopModel>
}