package com.example.k_pop.view

import com.example.k_pop.model.KPopModel

interface MainContract {
    interface View {
        fun showSongInfo(model: KPopModel)
        fun showError(message: String)
    }

    interface Presenter {
        fun searchSong(songName: String)
    }
}