package com.example.k_pop.model

import com.google.gson.annotations.SerializedName

data class KPopModel(
    var data : List<SongModel>
)

data class SongModel(
    @SerializedName("Artist")
    var artist : String,
    @SerializedName("Song Name")
    var songName : String,
    @SerializedName("video")
    var video : String
)